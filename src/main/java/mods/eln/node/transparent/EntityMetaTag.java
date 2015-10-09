package mods.eln.node.transparent;

/**
 * Created by svein on 10/10/15.
 */
public enum EntityMetaTag {
    Basic(0, TransparentNodeEntity.class),
    Fluid(1, TransparentNodeEntityWithFluid.class);

    public final int meta;
    public final Class cls;

    EntityMetaTag(int meta, Class cls) {
        this.meta = meta;
        this.cls = cls;
    }
}
