package mods.eln.mechanical

import mods.eln.misc.Coordonate
import mods.eln.misc.Direction
import mods.eln.misc.INBTTReady
import mods.eln.node.NodeManager
import mods.eln.sim.process.destruct.ShaftSpeedWatchdog
import net.minecraft.nbt.NBTTagCompound
import java.util.*


// Speed above which shafts will (by default) explode.
val absoluteMaximumShaftSpeed = 1000.0
// "Standard" drag, in J/t per rad.
val defaultDrag = 0.02


/**
 * Represents the connection between all blocks that are part of the same shaft network.
 */
public class ShaftNetwork(): INBTTReady {
    val elements = HashSet<ShaftElement>()

    constructor(first: ShaftElement) : this() {
        elements.add(first)
    }

    // Aggregate properties of the (current) shaft:
    val shapeFactor = 0.5
    val mass: Double
      get() {
          var sum = 0.0
          for (e in elements) {
              sum += e.shaftMass
          }
          return sum
      }
    var rads = 0.0
    var radsLastPublished = rads

    val joulePerRad: Double
        get() = mass * mass * shapeFactor / 2

    var energy: Double
        get() = joulePerRad * rads
        set(value) {
            rads = value / joulePerRad
            if (rads < 0) rads = 0.0
            if (radsLastPublished > rads * 1.05 || radsLastPublished < rads * 0.95) {
                elements.forEach { it.needPublish() }
                radsLastPublished = rads
            }
        }

    /**
     * Merge two shafts.
     *
     * @param other The shaft to merge into this one. Destroyed.
     */
    fun mergeShafts(other: ShaftNetwork) {
        // TODO: Some kind of explosion-effect for severely mismatched speeds?
        // For now, let's be nice.
        rads = Math.min(rads, other.rads)

        assert(other != this)
        for (element in other.elements) {
            elements.add(element)
            element.shaft = this
        }
        other.elements.clear()
    }

    /**
     * Connect a ShaftElement to a shaft network, merging any relevant adjacent networks.
     * @param from The ShaftElement that changed.
     */
    fun connectShaft(from: ShaftElement) {
        assert(from in elements)
        val neighbours = getNeighbours(from)
        for (neighbour in neighbours) {
            if (neighbour.shaft != this) {
                mergeShafts(neighbour.shaft)
            }
        }
    }

    /**
     * Disconnect from a shaft network, because an element is dying.
     * @param from The IShaftElement that's going away.
     */
    fun disconnectShaft(from: ShaftElement) {
        elements.remove(from)
        // Going away momentarily, but...
        from.shaft = ShaftNetwork(from)
        // This may have split the network.
        // At the moment there's no better way to figure this out than by exhaustively walking it to check for partitions.
        rebuildNetwork()
    }

    /**
     * Walk the entire network, splitting as necessary.
     * Yes, this makes breaking a shaft block O(n). Not a problem right now.
     */
    internal fun rebuildNetwork() {
        val unseen = HashSet<ShaftElement>(elements)
        val queue = HashSet<ShaftElement>()
        var shaft = this;
        while (unseen.size() > 0) {
            shaft.elements.clear();
            // Do a breadth-first search from an arbitrary element.
            val start = unseen.iterator().next()
            unseen.remove(start);
            queue.add(start);
            while (queue.size() > 0) {
                val next = queue.iterator().next()
                queue.remove(next);
                shaft.elements.add(next);
                next.shaft = shaft
                val neighbours = getNeighbours(next)
                for (neighbour in neighbours) {
                    if (unseen.contains(neighbour)) {
                        unseen.remove(neighbour)
                        queue.add(neighbour)
                    }
                }
            }
            // We ran out of network. Any elements remaining in unseen should thus form a new network.
            shaft = ShaftNetwork()
        }
    }

    private fun getNeighbours(from: ShaftElement): ArrayList<ShaftElement> {
        val c = Coordonate()
        val ret = ArrayList<ShaftElement>(6)
        for (dir in from.shaftConnectivity) {
            c.copyFrom(from.coordonate())
            c.move(dir)
            val to = NodeManager.instance!!.getTransparentNodeFromCoordinate(c)
            if (to is ShaftElement) {
                for (dir2 in to.shaftConnectivity) {
                    if (dir2.getInverse() == dir) {
                        ret.add(to)
                        break
                    }
                }
            }
        }
        return ret
    }

    override fun readFromNBT(nbt: NBTTagCompound, str: String?) {
        rads = nbt.getFloat(str + "rads").toDouble()
    }

    override fun writeToNBT(nbt: NBTTagCompound, str: String?) {
        nbt.setFloat(str + "rads", rads.toFloat())
    }

}

interface ShaftElement {
    var shaft: ShaftNetwork
    val shaftMass: Double
    val shaftConnectivity: Array<Direction>
    fun coordonate(): Coordonate

    fun initialize() {
        shaft.connectShaft(this)
    }

    fun needPublish()
}

public fun createShaftWatchdog(shaftElement: ShaftElement): ShaftSpeedWatchdog {
    return ShaftSpeedWatchdog(shaftElement, absoluteMaximumShaftSpeed)
}
