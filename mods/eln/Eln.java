package mods.eln;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import org.bouncycastle.asn1.esf.CompleteRevocationRefs;

import mods.eln.TreeResinCollector.TreeResinCollectorBlock;
import mods.eln.TreeResinCollector.TreeResinCollectorDescriptor;
import mods.eln.TreeResinCollector.TreeResinCollectorTileEntity;
import mods.eln.autominer.AutoMinerDescriptor;
import mods.eln.battery.BatteryDescriptor;
import mods.eln.batterycharger.BatteryChargerDescriptor;
import mods.eln.cable.CableRenderDescriptor;
import mods.eln.client.ClientKeyHandler;
import mods.eln.client.ClientProxy;
import mods.eln.client.FrameTime;
import mods.eln.client.SoundLoader;
import mods.eln.diode.DiodeDescriptor;
import mods.eln.eggincubator.EggIncubatorDescriptor;
import mods.eln.electricalalarm.ElectricalAlarmDescriptor;
import mods.eln.electricalantennarx.ElectricalAntennaRxDescriptor;
import mods.eln.electricalantennatx.ElectricalAntennaTxDescriptor;
import mods.eln.electricalbreaker.ElectricalBreakerDescriptor;
import mods.eln.electricalcable.ElectricalCableDescriptor;
import mods.eln.electricaldatalogger.DataLogsPrintDescriptor;
import mods.eln.electricaldatalogger.ElectricalDataLoggerDescriptor;
import mods.eln.electricalfurnace.ElectricalFurnaceDescriptor;
import mods.eln.electricalfurnace.ElectricalFurnaceElement;
import mods.eln.electricalfurnace.ElectricalFurnaceRender;
import mods.eln.electricalgatesource.ElectricalGateSourceDescriptor;
import mods.eln.electricallightsensor.ElectricalLightSensorDescriptor;
import mods.eln.electricalmachine.CompressorDescriptor;
import mods.eln.electricalmachine.MagnetizerDescriptor;
import mods.eln.electricalmachine.PlateMachineDescriptor;
import mods.eln.electricalmachine.ElectricalMachineDescriptor;
import mods.eln.electricalmachine.MaceratorDescriptor;
import mods.eln.electricalmath.ElectricalMathDescriptor;
import mods.eln.electricalredstoneinput.ElectricalRedstoneInputDescriptor;
import mods.eln.electricalredstoneoutput.ElectricalRedstoneOutputDescriptor;
import mods.eln.electricalrelay.ElectricalRelayDescriptor;
import mods.eln.electricalrelay.ElectricalRelayElement;
import mods.eln.electricalsource.ElectricalSourceDescriptor;
import mods.eln.electricalsource.ElectricalSourceElement;
import mods.eln.electricalsource.ElectricalSourceRender;
import mods.eln.electricalswitch.ElectricalSwitchDescriptor;
import mods.eln.electricaltimout.ElectricalTimeoutDescriptor;
import mods.eln.electricalvumeter.ElectricalVuMeterDescriptor;
import mods.eln.electricasensor.ElectricalSensorDescriptor;
import mods.eln.elnhttpserver.ElnHttpServer;
import mods.eln.generic.GenericItemBlockUsingDamageDescriptor;
import mods.eln.generic.GenericItemUsingDamage;
import mods.eln.generic.GenericItemUsingDamageDescriptor;
import mods.eln.generic.GenericItemUsingDamageDescriptorWithComment;
import mods.eln.generic.SharedItem;
import mods.eln.generic.genericArmorItem;
import mods.eln.ghost.GhostBlock;

import mods.eln.ghost.GhostGroup;
import mods.eln.ghost.GhostManager;
import mods.eln.groundcable.GroundCableDescriptor;
import mods.eln.groundcable.GroundCableElement;
import mods.eln.groundcable.GroundCableRender;
import mods.eln.heatfurnace.HeatFurnaceDescriptor;
import mods.eln.heatfurnace.HeatFurnaceElement;
import mods.eln.heatfurnace.HeatFurnaceRender;
import mods.eln.intelligenttransformer.IntelligentTransformerElement;
import mods.eln.intelligenttransformer.IntelligentTransformerRender;
import mods.eln.item.BrushDescriptor;
import mods.eln.item.CombustionChamber;
import mods.eln.item.DynamoDescriptor;
import mods.eln.item.ElectricalDrillDescriptor;

import mods.eln.item.FerromagneticCoreDescriptor;
import mods.eln.item.HeatingCorpElement;
import mods.eln.item.LampDescriptor;
import mods.eln.item.MaceratorSorterDescriptor;
import mods.eln.item.MachineBoosterDescriptor;
import mods.eln.item.MeterItemArmor;
import mods.eln.item.MiningPipeDescriptor;
import mods.eln.item.OreScanner;
import mods.eln.item.OverHeatingProtectionDescriptor;
import mods.eln.item.OverVoltageProtectionDescriptor;
import mods.eln.item.SixNodeCacheItem;
import mods.eln.item.SolarTrackerDescriptor;
import mods.eln.item.ThermalIsolatorElement;
import mods.eln.item.ToolsSetItem;
import mods.eln.item.TreeResin;

import mods.eln.item.LampDescriptor.Type;

import mods.eln.item.electricalinterface.ItemEnergyInventoryProcess;
import mods.eln.item.electricalitem.BatteryItem;
import mods.eln.item.electricalitem.ElectricalArmor;
import mods.eln.item.electricalitem.ElectricalAxe;
import mods.eln.item.electricalitem.ElectricalLampItem;
import mods.eln.item.electricalitem.ElectricalPickaxe;
import mods.eln.item.electricalitem.ElectricalTool;
import mods.eln.item.electricalitem.LampItem;
import mods.eln.item.regulator.IRegulatorDescriptor;
import mods.eln.item.regulator.RegulatorAnalogDescriptor;
import mods.eln.item.regulator.RegulatorOnOffDescriptor;
import mods.eln.lampsocket.LampSocketDescriptor;
import mods.eln.lampsocket.LampSocketType;
import mods.eln.lampsocket.LightBlock;
import mods.eln.lampsocket.LightBlockEntity;
import mods.eln.lampsupply.LampSupplyDescriptor;
import mods.eln.lampsupply.LampSupplyElement;

import mods.eln.misc.Coordonate;
import mods.eln.misc.Direction;
import mods.eln.misc.FunctionTable;
import mods.eln.misc.FunctionTableYProtect;
import mods.eln.misc.IFunction;
import mods.eln.misc.Obj3D;
import mods.eln.misc.Obj3DFolder;
import mods.eln.misc.Recipe;
import mods.eln.misc.RecipesList;
import mods.eln.misc.TileEntityDestructor;
import mods.eln.misc.Utils;
import mods.eln.mppt.MpptDescriptor;
import mods.eln.node.NodeBlock;
import mods.eln.node.NodeBlockItemWithSubTypes;
import mods.eln.node.NodeManager;
import mods.eln.node.NodeServer;
import mods.eln.node.SixNode;
import mods.eln.node.SixNodeBlock;
import mods.eln.node.SixNodeDescriptor;
import mods.eln.node.SixNodeElement;
import mods.eln.node.SixNodeEntity;
import mods.eln.node.SixNodeItem;
import mods.eln.node.TransparentNode;
import mods.eln.node.TransparentNodeBlock;
import mods.eln.node.TransparentNodeDescriptor;
import mods.eln.node.TransparentNodeElement;
import mods.eln.node.TransparentNodeEntity;
import mods.eln.node.TransparentNodeItem;
import mods.eln.ore.OreBlock;
import mods.eln.ore.OreDescriptor;
import mods.eln.ore.OreItem;
import mods.eln.sim.ElectricalLoad;
import mods.eln.sim.RegulatorType;
import mods.eln.sim.Simulator;
import mods.eln.sim.ThermalLoadInitializer;
import mods.eln.sim.ThermalLoadInitializerByPowerDrop;
import mods.eln.sim.ThermalRegulator;
import mods.eln.solarpannel.SolarPannelDescriptor;
import mods.eln.solver.ConstSymbole;
import mods.eln.solver.Equation;
import mods.eln.solver.ISymbole;
import mods.eln.teleporter.TeleporterDescriptor;
import mods.eln.teleporter.TeleporterElement;
import mods.eln.thermalcable.ThermalCableDescriptor;
import mods.eln.thermaldissipatoractive.ThermalDissipatorActiveDescriptor;
import mods.eln.thermaldissipatorpassive.ThermalDissipatorPassiveDescriptor;
import mods.eln.thermalsensor.ThermalSensorDescriptor;
import mods.eln.transformer.TransformerDescriptor;
import mods.eln.transformer.TransformerElement;
import mods.eln.transformer.TransformerRender;
import mods.eln.turbine.TurbineCoreDescriptor;
import mods.eln.turbine.TurbineDescriptor;
import mods.eln.turbine.TurbineElement;
import mods.eln.turbine.TurbineRender;
import mods.eln.windturbine.WindTurbineDescriptor;

import mods.eln.wirelesssignal.WirelessSignalAnalyserItemDescriptor;
import mods.eln.wirelesssignal.WirelessSignalRxDescriptor;
import mods.eln.wirelesssignal.WirelessSignalRxElement;
import mods.eln.wirelesssignal.WirelessSignalTxDescriptor;
import mods.eln.wirelesssignal.WirelessSignalTxElement;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeDummyContainer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;



@Mod(modid = "Eln", name = "Eln", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "miaouMod" }, packetHandler = PacketHandler.class)
public class Eln {
	/*
	 * Eln() { instance = this; }
	 */
	public static String channelName = "miaouMod";

	public static final double networkSerializeValueFactor = 100.0;

	public static final byte packetNodeSerialized24bitPosition = 11;
	public static final byte packetNodeSerialized48bitPosition = 12;
	public static final byte packetNodeRefreshRequest = 13;
	public static final byte packetPlayerKey = 14;
	public static final byte packetNodeSingleSerialized = 15;
	public static final byte packetPublishForNode = 16;
	public static final byte packetOpenLocalGui = 17;
	public static final byte packetForClientNode = 18;

	static PacketHandler packetHandler;
	static NodeServer nodeServer;
	public static ClientKeyHandler clientKeyHandler;
	public static GhostManager ghostManager;
	private static NodeManager nodeManager;
	public static PlayerManager playerManager;

	public static Simulator simulator = null;

	public static CreativeTabs creativeTab;

	public final static int blocBaseId = 220;
	public final static int itemBaseId = 7260;
	/* public static ItemArmor helmetLeather = (ItemArmor)(new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0)).setUnlocalizedName("helmetCloth").func_111206_d("leather_helmet");
	    public static ItemArmor plateLeather = (ItemArmor)(new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1)).setUnlocalizedName("chestplateCloth").func_111206_d("leather_chestplate");
	    public static ItemArmor legsLeather = (ItemArmor)(new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2)).setUnlocalizedName("leggingsCloth").func_111206_d("leather_leggings");
	    public static ItemArmor bootsLeather = (ItemArmor)(new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3)).setUnlocalizedName("bootsCloth").func_111206_d("leather_boots");
	   
	
	*/
	
	
	
	public static int helmetCopperId,plateCopperId,legsCopperId,bootsCopperId;
	public static ItemArmor helmetCopper,plateCopper,legsCopper,bootsCopper;

	public static int helmetECoalId,plateECoalId,legsECoalId,bootsECoalId;
	public static ItemArmor helmetECoal,plateECoal,legsECoal,bootsECoal;

	
	public static int swordCopperId,hoeCopperId,shovelCopperId,pickaxeCopperId,axeCopperId;
	public static Item swordCopper,hoeCopper,shovelCopper,pickaxeCopper,axeCopper;
	
	//public static int electricalPickaxeId;
//	public static Item electricalPickaxe;
	
	
	
	public static Item voltMeterHelmet;
	public static Item thermoMeterHelmet;
	public static Item currentMeterHelmet;
	public static Item toolsSetItem;
	public static Item brushItem;
	// public static LampItem lampItem;

	/*
	 * public static GenericItemUsingDamage<HeatingCorpElement> heatingCorpItem;
	 * public static GenericItemUsingDamage<ThermalIsolatorElement>
	 * thermalIsolatorItem; public static
	 * GenericItemUsingDamage<RegulatorElement> regulatorItem;
	 */

	public static SharedItem sharedItem;
	public static SharedItem sharedItemStackOne;

	public static SixNodeBlock sixNodeBlock;
	public static TransparentNodeBlock transparentNodeBlock;
	public static OreBlock oreBlock;
	public static GhostBlock ghostBlock;
	public static LightBlock lightBlock;

	public static SixNodeItem sixNodeItem;
	public static TransparentNodeItem transparentNodeItem;
	public static OreItem oreItem;
	// public static TreeResinCollectorBlock treeResinCollectorBlock;

	// The instance of your mod that Forge uses.
	@Instance("Eln")
	public static Eln instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "mods.eln.client.ClientProxy", serverSide = "mods.eln.CommonProxy")
	public static CommonProxy proxy;

	public int electricalOverSampling, thermalOverSampling = 1,
			commonOverSampling = 20;

	public ElectricalCableDescriptor highVoltageCableDescriptor;
	public ElectricalCableDescriptor signalCableDescriptor;
	public ElectricalCableDescriptor lowVoltageCableDescriptor;
	public ElectricalCableDescriptor meduimVoltageCableDescriptor;

	int creativeTabId;
	int brushItemId;

	int oreId;
	int sharedItemId,sharedItemStackOneId;
	int transparentNodeBlockId;
	int SixNodeBlockId;
	int ghostBlockId;
	public static int lightBlockId;

	// int TreeResinCollectorId;

	public static Obj3DFolder obj = new Obj3DFolder();

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		ArrayList<ISymbole> symboleList = new ArrayList<ISymbole>();
		symboleList.add(new ConstSymbole("A",0.1));
		symboleList.add(new ConstSymbole("B",0.2));
		symboleList.add(new ConstSymbole("C",0.3));
		double value = 0.0;
		//Equation equ = new Equation("abs(-A) + B * (-0.4 - 0.5 + C)^3 + 0.5 * A",symboleList,100);
		Equation equ = new Equation("1+periodic(2,1)*2",symboleList,100);
		if(equ.isValid()){
			value = equ.getValue();
		}
		
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.CLIENT)
			MinecraftForge.EVENT_BUS.register(new SoundLoader());

		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();

		creativeTabId = config.getItem("itemCreativeTab", itemBaseId - 1)
				.getInt();
		brushItemId = config.getItem("brushItem", itemBaseId - 1).getInt();
		
		helmetCopperId = config.getItem("helmetCopperId", itemBaseId + 2).getInt();
		plateCopperId = config.getItem("plateCopperId", itemBaseId + 3).getInt();
		legsCopperId = config.getItem("legsCopperId", itemBaseId + 4).getInt();
		bootsCopperId = config.getItem("bootsCopperId", itemBaseId + 5).getInt();

		swordCopperId = config.getItem("swordCopperId", itemBaseId + 6).getInt();
		hoeCopperId = config.getItem("hoeCopperId", itemBaseId + 7).getInt();
		shovelCopperId = config.getItem("shovelCopperId", itemBaseId + 8).getInt();
		pickaxeCopperId = config.getItem("pickaxeCopperId", itemBaseId + 9).getInt();
		axeCopperId = config.getItem("axeCopperId", itemBaseId + 10).getInt();
		
		helmetECoalId = config.getItem("helmetECoalId", itemBaseId + 11).getInt();
		plateECoalId = config.getItem("plateECoalId", itemBaseId + 12).getInt();
		legsECoalId = config.getItem("legsECoalId", itemBaseId + 13).getInt();
		bootsECoalId = config.getItem("bootsECoalId", itemBaseId + 14).getInt();

		
	//	electricalPickaxeId =  config.getItem("axeCopperId", itemBaseId + 11).getInt();
		
		
		sharedItemId = config.getItem("sharedItem", itemBaseId + 18).getInt();
		sharedItemStackOneId = config.getItem("sharedItemStackOne", itemBaseId + 19).getInt();



		

		oreId = config.getTerrainBlock("ELN", "OreBlock", blocBaseId + 7,
				"choubakaka").getInt();
		transparentNodeBlockId = config.getTerrainBlock("ELN",
				"transparentNodeBlock", blocBaseId + 6, "choubakaka").getInt();
		SixNodeBlockId = config.getTerrainBlock("ELN", "sixNodeBlock",
				blocBaseId + 1, "choubakaka").getInt();

		ghostBlockId = config.getTerrainBlock("ELN", "GhostBlock",
				blocBaseId + 8, "choubakaka").getInt();

		lightBlockId = config.getTerrainBlock("ELN", "LightBlock",
				blocBaseId + 2, "choubakaka").getInt();



		
		
		
		electricalOverSampling = config.get("Simulator", "ElectricalHz", 8000)
				.getInt() / commonOverSampling / 20;
		if (electricalOverSampling < 1)
			electricalOverSampling = 1;

		config.save();
	}

	public FrameTime frameTime;

	@Init
	public void load(FMLInitializationEvent event) {

		simulator = new Simulator(20, commonOverSampling,
				electricalOverSampling, thermalOverSampling);
		playerManager = new PlayerManager();
		tileEntityDestructor = new TileEntityDestructor();
		
		nodeServer = new NodeServer();
		frameTime = new FrameTime();
		packetHandler = new PacketHandler();
		// ForgeDummyContainer
		instance = this;
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

		Item itemCreativeTab = new Item(creativeTabId).setMaxStackSize(1)
				.setUnlocalizedName("eln:ElnCreativeTab")
				.func_111206_d("eln:ElnCreativeTab");

		creativeTab = new GenericCreativeTab("Eln", itemCreativeTab);

		oreBlock = (OreBlock) new OreBlock(oreId).setCreativeTab(creativeTab);

		sharedItem = (SharedItem) new SharedItem(sharedItemId)
				.setCreativeTab(creativeTab).setMaxStackSize(64)
				.setUnlocalizedName("sharedItem");

		sharedItemStackOne = (SharedItem) new SharedItem(sharedItemStackOneId)
			.setCreativeTab(creativeTab).setMaxStackSize(1)
			.setUnlocalizedName("sharedItemStackOne");

		transparentNodeBlock = (TransparentNodeBlock) new TransparentNodeBlock(
				transparentNodeBlockId, Material.ground,
				TransparentNodeEntity.class).setCreativeTab(creativeTab);
		sixNodeBlock = (SixNodeBlock) new SixNodeBlock(SixNodeBlockId,
				Material.ground, SixNodeEntity.class)
				.setCreativeTab(creativeTab);

		ghostBlock = (GhostBlock) new GhostBlock(ghostBlockId);
		lightBlock = (LightBlock) new LightBlock(lightBlockId);

		obj.loadFolder("eln", "/model");
		// Obj3DFolder miaou = new Obj3DFolder();
		// miaou.loadFolder("/mods/eln/model");

		// GameRegistry.addRecipe(new ItemStack(treeResinCollectorBlock,1), "X",
		// Character.valueOf('X'), Block.dirt);
		/*
		 * LanguageRegistry.instance().addStringLocalization(
		 * "itemGroup.ElnCreativeTab", "en_US", "Eln tab");
		 * LanguageRegistry.addName(multimeterItem, "Multimeter");
		 * LanguageRegistry.addName(allMeterItem, "AllMeterItem");
		 * LanguageRegistry.addName(toolsSetItem, "Tools set");
		 * LanguageRegistry.addName(voltMeterHelmet,"VoltMeter Helmet");
		 * LanguageRegistry.addName(thermoMeterHelmet,"ThermoMeter Helmet");
		 * LanguageRegistry.addName(currentMeterHelmet,"Current Helmet");
		 */
		GameRegistry.registerBlock(sixNodeBlock, SixNodeItem.class);
		TileEntity.addMapping(SixNodeEntity.class, "SixNodeEntity");

		GameRegistry.registerBlock(transparentNodeBlock,
				TransparentNodeItem.class);
		TileEntity.addMapping(TransparentNodeEntity.class,
				"TransparentNodeEntity");

		GameRegistry.registerBlock(oreBlock, OreItem.class);

	//	TileEntity.addMapping(GhostEntity.class, "GhostEntity");
		TileEntity.addMapping(LightBlockEntity.class, "LightBlockEntity");

		NodeManager.registerBlock(sixNodeBlock, SixNode.class);
		NodeManager.registerBlock(transparentNodeBlock, TransparentNode.class);

		sixNodeItem = (SixNodeItem) Item.itemsList[sixNodeBlock.blockID];
		transparentNodeItem = (TransparentNodeItem) Item.itemsList[transparentNodeBlock.blockID];

		oreItem = (OreItem) Item.itemsList[oreBlock.blockID];
		/*
		 * 
		 * int id = 0,subId = 0,completId; String name;
		 */

		registerArmor();
		registerTool();
		registerOre();

		registerGround(2);
		registerElectricalSource(3);
		registerElectricalCable(32);
		registerThermalCable(48);
		registerLampSocket(64);
		registerLampSupply(65);
		registerBatteryCharger(66);
		registerWirelessSignal(92);
		registerElectricalDataLogger(93);
		registerElectricalRelay(94);
		registerElectricalGateSource(95);
		registerDiode(96);
		registerSwitch(97);
		registerElectricalBreaker(98);
		registerElectricalSensor(100);
		registerThermalSensor(101);
		registerElectricalVuMeter(102);
		registerElectricalAlarm(103);
		registerElectricalLightSensor(104);
		registerElectricalRedstone(108);
		registerElectricalGate(109); 
		registerTreeResinCollector(116);

		registerTransformer(2);
		registerHeatFurnace(3);
		registerTurbine(4);
	//	registerIntelligentTransformer(5);
	//	registerMppt(6);
		registerElectricalAntenna(7);
		registerBattery(16);
		registerElectricalFurnace(32);
		registerMacerator(33);
		//registerExtractor(34);
		registerCompressor(35);
		registermagnetiser(36);
		registerPlateMachine(37);
		registerEggIncubator(41);
		registerAutoMiner(42);
		registerSolarPannel(48);
		registerWindTurbine(49);
		registerThermalDissipatorPassiveAndActive(64);
		registerTransparentNodeMisc(65);

		registerHeatingCorp(1);
		registerThermalIsolator(2);
		registerRegulatorItem(3);
		registerLampItem(4);
		registerProtection(5);
		registerCombustionChamber(6);
		registerFerromagneticCore(7);
		registerIngot(8);
		registerDust(9);
		registerElectricalMotor(10);
		registerSolarTracker(11);


		registerMeter(14);
		registerElectricalDrill(15);
		registerOreScanner(16);
		registerMiningPipe(17);
		registerSixNodeCache(18);
		registerTreeResinAndRubber(64);
		registerRawCable(65);
		registerBrush(119);
		registerMiscItem(120);
		registerElectricalTool(121);
		registerBatteryItem(122);

		
		recipeArmor();
		recipeTool();
		
		recipeGround();
		recipeElectricalSource();
		recipeElectricalCable();
		recipeThermalCable();
		recipeLampSocket();
		recipeLampSupply();
		recipeDiode();
		recipeSwitch();
		recipeWirelessSignal();
		recipeElectricalRelay();
		recipeElectricalDataLogger();
		recipeElectricalGateSource();
		recipeElectricalBreaker();
		recipeElectricalVuMeter();
		recipeElectricalLightSensor();
		recipeElectricalRedstone();
		recipeElectricalGate();
		recipeElectricalAlarm();
		recipeSixNodeCache();
		recipeElectricalSensor();
		/*
		registerGround(2);
		registerElectricalSource(3);
		registerElectricalCable(32);
		registerThermalCable(48);
		registerLampSocket(64);
		registerWirelessSignal(92);
		registerElectricalDataLogger(93);
		registerElectricalRelay(94);
		registerElectricalGateSource(95);
		registerDiode(96);
		registerSwitch(97);
		registerElectricalBreaker(98);
		registerElectricalSensor(100);
		registerThermalSensor(101);
		registerElectricalVuMeter(102);
		registerElectricalAlarm(103);
		registerElectricalLightSensor(104);
		registerElectricalRedstone(108);
		registerElectricalGate(109);
		registerTreeResinCollector(116);
		*/
		recipeMachine();
		recipeTransformer();
		recipeHeatFurnace();
		recipeTurbine();
		recipeBattery();
		recipeElectricalFurnace(); 
		recipeAutoMiner();
		recipeSolarPannel();

		recipeThermalDissipatorPassiveAndActive();
		recipeElectricalAntenna();
		recipeEggIncubatore();
		recipeBatteryCharger();
		
		/*		registerTransformer(2);
		registerHeatFurnace(3);
		registerTurbine(4);
		registerIntelligentTransformer(5);
		registerMppt(6);
		registerElectricalAntenna(7);
		registerBattery(16);
		registerElectricalFurnace(32);
		registerMacerator(33);
		registerExtractor(34);
		registerCompressor(35);
		registermagnetiser(36);
		registerAutoMiner(42);
		registerSolarPannel(48);
		registerWindTurbine(49);
		registerThermalDissipatorPassiveAndActive(64);
		*/
		
		recipeGeneral();
		recipeHeatingCorp();
		recipeThermalIsolator();
		recipeRegulatorItem();
		recipeLampItem();
		recipeProtection();
		recipeCombustionChamber();
		recipeFerromagneticCore();
		recipeIngot();
		recipeDust();
		recipeElectricalMotor();
		recipeSolarTracker();
		recipeDynamo();
		recipeWindRotor();
		recipeMeter();
		recipeElectricalDrill();
		recipeOreScanner();
		recipeMiningPipe();
		recipeTreeResinAndRubber();
		recipeRawCable();
		recipeMiscItem();
		recipeBatteryItem();
		recipeElectricalTool();
		recipePortableCondensator();

		recipeFurnace();
		recipeMacerator();
		//recipeExtractor();
		recipeCompressor();
		recipePlateMachine();
		recipemagnetiser();

		proxy.registerRenderers();

		try {
			elnHttpServer = new ElnHttpServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}









	ElnHttpServer elnHttpServer;

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method

	}

	@cpw.mods.fml.common.Mod.ServerStopping
	/* Remember to use the right event! */
	public void onServerStopping(FMLServerStoppingEvent ev) {
		LightBlockEntity.observers.clear();
		TeleporterElement.teleporterList.clear();
		playerManager.clear();
		MinecraftServer server = FMLCommonHandler.instance()
				.getMinecraftServerInstance();
		WorldServer worldServer = server.worldServers[0];
		simulator.init();
		nodeServer.init();
		nodeManager = null;
		ghostManager = null;
	}

	public TileEntityDestructor tileEntityDestructor;
	
	@ServerStarting
	/* Remember to use the right event! */
	public void onServerStarting(FMLServerStartingEvent ev) {
		TeleporterElement.teleporterList.clear();
		tileEntityDestructor.clear();
		LightBlockEntity.observers.clear();
		WirelessSignalTxElement.channelMap.clear();
		LampSupplyElement.channelMap.clear();
		playerManager.clear();
		MinecraftServer server = FMLCommonHandler.instance()
				.getMinecraftServerInstance();
		WorldServer worldServer = server.worldServers[0];
		simulator.init();

		simulator.addSlowProcess(new ItemEnergyInventoryProcess());
		
		ghostManager = (GhostManager) worldServer.mapStorage.loadData(
				GhostManager.class, "GhostManager");
		if (ghostManager == null) {
			ghostManager = new GhostManager("GhostManager");
			worldServer.mapStorage.setData("GhostManager", ghostManager);
		}
		ghostManager.init();

		nodeManager = (NodeManager) worldServer.mapStorage.loadData(
				NodeManager.class, "NodeManager");
		if (nodeManager == null) {
			nodeManager = new NodeManager("NodeManager");
			worldServer.mapStorage.setData("NodeManager", nodeManager);
		}

		nodeServer.init();


		

	}

	@cpw.mods.fml.common.Mod.ServerStopping
	public void ServerStopping(FMLServerStoppingEvent ev) {
		
		playerManager.clear();

		nodeServer.stop();

		simulator.stop();
		tileEntityDestructor.clear();
		LightBlockEntity.observers.clear(); //?
		LampSupplyElement.channelMap.clear();
		WirelessSignalTxElement.channelMap.clear();

	}

	public CableRenderDescriptor stdCableRenderSignal;
	public CableRenderDescriptor stdCableRender50V;
	public CableRenderDescriptor stdCableRender200V;
	public CableRenderDescriptor stdCableRender800V;

	public static double gateOutputCurrent = 0.100;
	public static final double SVU = 50, SVII = gateOutputCurrent / 10,
			SVUinv = 1.0 / SVU;
	public static final double LVU = 50;
	public static final double MVU = 200;
	public static final double HVU = 800;

	public static final double SVP = gateOutputCurrent * SVU;
	public static final double LVP = 1000;
	public static final double MVP = 2000;
	public static final double HVP = 4000;

	public static final double cableHeatingTime = 30;
	public static final double cableWarmLimit = 130;
	public static final double cableThermalConductionTao = 0.5;
	public static final ThermalLoadInitializer cableThermalLoadInitializer = new ThermalLoadInitializer(
			cableWarmLimit, -100, cableHeatingTime, cableThermalConductionTao);
	public static final ThermalLoadInitializer sixNodeThermalLoadInitializer = new ThermalLoadInitializer(
			cableWarmLimit, -100, cableHeatingTime, 1000);

	void registerElectricalCable(int id) {
		int subId, completId;
		String name;

		CableRenderDescriptor render;
		ElectricalCableDescriptor desc;
		{
			subId = 0;

			name = "Signal cable";

			stdCableRenderSignal = new CableRenderDescriptor("eln",
					"/sprites/CABLE.PNG", 0.95f, 0.95f);

			desc = new ElectricalCableDescriptor(name, stdCableRenderSignal,
					"For signal transmition", true);

			signalCableDescriptor = desc;

			desc.setPhysicalConstantLikeNormalCable(SVU, SVP, 0.02 / 20
					* gateOutputCurrent / SVII,// electricalNominalVoltage,
												// electricalNominalPower,
												// electricalNominalPowerDrop,
					SVU * 1.3, SVP * 1.2,// electricalMaximalVoltage,
											// electricalMaximalPower,
					0.5,// electricalOverVoltageStartPowerLost,
					cableWarmLimit, -100,// thermalWarmLimit, thermalCoolLimit,
					cableHeatingTime, 1// thermalNominalHeatTime, thermalConductivityTao
			);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
			// GameRegistry.registerCustomItemStack(name, desc.newItemStack(1));

		}

		{
			subId = 4;

			name = "Low voltage cable";

			stdCableRender50V = new CableRenderDescriptor("eln",
					"/sprites/CABLE.PNG", 1.95f, 0.95f);

			desc = new ElectricalCableDescriptor(name, stdCableRender50V,
					"For low voltage with high current", false);

			lowVoltageCableDescriptor = desc;

			desc.setPhysicalConstantLikeNormalCable(LVU, LVP, 0.2 / 20,// electricalNominalVoltage,
																		// electricalNominalPower,
																		// electricalNominalPowerDrop,
					LVU * 1.3, LVP * 1.2,// electricalMaximalVoltage,
											// electricalMaximalPower,
					20,// electricalOverVoltageStartPowerLost,
					cableWarmLimit, -100,// thermalWarmLimit, thermalCoolLimit,
					cableHeatingTime, cableThermalConductionTao// thermalNominalHeatTime,
													// thermalConductivityTao
			);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);

		}

		{
			subId = 8;

			name = "Medium voltage cable";

			stdCableRender200V = new CableRenderDescriptor("eln",
					"/sprites/CABLE.PNG", 2.95f, 0.95f);

			desc = new ElectricalCableDescriptor(name, stdCableRender200V,
					"miaou", false);

			meduimVoltageCableDescriptor = desc;

			desc.setPhysicalConstantLikeNormalCable(MVU, MVP, 0.15 / 20,// electricalNominalVoltage,
																		// electricalNominalPower,
																		// electricalNominalPowerDrop,
					MVU * 1.3, MVP * 1.2,// electricalMaximalVoltage,
											// electricalMaximalPower,
					30,// electricalOverVoltageStartPowerLost,
					cableWarmLimit, -100,// thermalWarmLimit, thermalCoolLimit,
					cableHeatingTime, cableThermalConductionTao// thermalNominalHeatTime,
													// thermalConductivityTao
			);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);

		}
		{
			subId = 12;

			// highVoltageCableId = subId;
			name = "High voltage cable";

			stdCableRender800V = new CableRenderDescriptor("eln",
					"/sprites/CABLE.PNG", 3.95f, 1.95f);

			desc = new ElectricalCableDescriptor(name, stdCableRender800V,
					"miaou2", false);

			highVoltageCableDescriptor = desc;

			desc.setPhysicalConstantLikeNormalCable(HVU, HVP, 0.1 / 20,// electricalNominalVoltage,
																		// electricalNominalPower,
																		// electricalNominalPowerDrop,
					HVU * 1.3, HVP * 1.2,// electricalMaximalVoltage,
											// electricalMaximalPower,
					40,// electricalOverVoltageStartPowerLost,
					cableWarmLimit, -100,// thermalWarmLimit, thermalCoolLimit,
					cableHeatingTime, cableThermalConductionTao// thermalNominalHeatTime,
													// thermalConductivityTao
			);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);

		}
	}

	void registerThermalCable(int id) {
		int subId, completId;
		String name;

		{
			subId = 0;

			name = "Copper thermal cable";

			ThermalCableDescriptor desc = new ThermalCableDescriptor(name,
					1000 - 20, -200, // thermalWarmLimit, thermalCoolLimit,
					500, 2000, // thermalStdT, thermalStdPower,
					4, 400, 0.1,// thermalStdDrop, thermalStdLost, thermalTao,
					new CableRenderDescriptor("eln",
							"sprites/TEX_THERMALCABLEBASE.PNG", 4, 4),
					"Miaou !");// description

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Isolated copper thermal cable";

			ThermalCableDescriptor desc = new ThermalCableDescriptor(name,
					1000 - 20, -200, // thermalWarmLimit, thermalCoolLimit,
					500, 2000, // thermalStdT, thermalStdPower,
					4, 10, 0.1,// thermalStdDrop, thermalStdLost, thermalTao,
					new CableRenderDescriptor("eln",
							"sprites/TEX_THERMALCABLEBASE.PNG", 4, 4),
					"Miaou !");// description

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerBattery(int id) {
		int subId, completId;
		String name;
		double heatTIme = 30;
		double[] voltageFunctionTable = { 0.000, 0.9, 1.0, 1.025, 1.04, 1.05,
				2.0 };
		FunctionTable voltageFunction = new FunctionTable(voltageFunctionTable,
				6.0 / 5);
		double stdDischargeTime = 4 * 60;
		double stdU = LVU;
		double stdP = LVP / 4;
		double stdHalfLife = Utils.minecraftDay * 2;

		{
			subId = 0;
			name = "Cost oriented battery";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"LowCostBattery",lowVoltageCableDescriptor, 0.5, true,true, voltageFunction, stdU,
					stdP * 1.2, 0.000, // electricalU,
										// electricalPMax,electricalDischargeRate
					stdP, stdDischargeTime, 0.998, stdHalfLife, // electricalStdP,
																// electricalStdDischargeTime,
																// electricalStdEfficiency,
																// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"Cheap battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;
			name = "Capacity oriented battery";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"HighCapacityBattery",lowVoltageCableDescriptor, 0.5, true,true, voltageFunction,
					stdU / 4, stdP / 2 * 1.2, 0.000, // electricalU,
														// electricalPMax,electricalDischargeRate
					stdP / 2, stdDischargeTime * 8, 0.998, stdHalfLife, // electricalStdP,
																		// electricalStdDischargeTime,
																		// electricalStdEfficiency,
																		// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"the battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 2;
			name = "Voltage oriented battery";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"HighVoltageBattery",meduimVoltageCableDescriptor, 0.5, true,true, voltageFunction, stdU * 4,
					stdP * 1.2, 0.000, // electricalU,
										// electricalPMax,electricalDischargeRate
					stdP, stdDischargeTime, 0.998, stdHalfLife, // electricalStdP,
																// electricalStdDischargeTime,
																// electricalStdEfficiency,
																// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"the battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 3;
			name = "Current oriented battery";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"HighCurrentBattery",lowVoltageCableDescriptor, 0.5, true,true, voltageFunction, stdU,
					stdP * 1.2 * 4, 0.000, // electricalU,
											// electricalPMax,electricalDischargeRate
					stdP * 4, stdDischargeTime / 6, 0.998, stdHalfLife, // electricalStdP,
																		// electricalStdDischargeTime,
																		// electricalStdEfficiency,
																		// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"the battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 4;
			name = "Life oriented battery";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"LongLifeBattery",lowVoltageCableDescriptor, 0.5, true,true, voltageFunction, stdU,
					stdP * 1.2, 0.000, // electricalU,
										// electricalPMax,electricalDischargeRate
					stdP, stdDischargeTime, 0.998, stdHalfLife * 8, // electricalStdP,
																	// electricalStdDischargeTime,
																	// electricalStdEfficiency,
																	// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"the battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 5;
			name = "Single usage battery";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"LongLifeBattery",lowVoltageCableDescriptor, 1.0, false,false, voltageFunction, stdU,
					stdP * 1.2 * 2, 0.000, // electricalU,
											// electricalPMax,electricalDischargeRate
					stdP * 2, stdDischargeTime, 0.998, stdHalfLife * 8, // electricalStdP,
																		// electricalStdDischargeTime,
																		// electricalStdEfficiency,
																		// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"the battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 32;
			name = "50V condensator";

			BatteryDescriptor desc = new BatteryDescriptor(name,
					"LongLifeBattery",lowVoltageCableDescriptor, 0.0, true,false,
					voltageFunction,
					stdU,stdP * 1.2 * 8, 0.005, // electricalU,// electricalPMax,electricalDischargeRate
					stdP * 8, 4, 0.998, stdHalfLife , // electricalStdP,
																		// electricalStdDischargeTime,
																		// electricalStdEfficiency,
																		// electricalStdHalfLife,
					heatTIme, 60, -100, // thermalHeatTime, thermalWarmLimit,
									// thermalCoolLimit,
					"the battery" // name, description)
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		
	}

	void registerGround(int id) {
		int subId, completId;
		String name;

		{
			subId = 0;
			name = "Ground cable";

			GroundCableDescriptor desc = new GroundCableDescriptor(name,obj.getObj("groundcable"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalSource(int id) {
		int subId, completId;
		String name;

		{
			subId = 0;
			name = "ElectricalSource";

			ElectricalSourceDescriptor desc = new ElectricalSourceDescriptor(
					name,obj.getObj("voltagesource"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerLampSocket(int id) {
		int subId, completId;
		String name;

		{
			subId = 0;

			name = "Lamp socket A";

			LampSocketDescriptor desc = new LampSocketDescriptor(name,
					obj.getObj("ClassicLampSocket"),false, LampSocketType.Douille, // LampSocketType
																	// socketType
					4, 0, 0, 0);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Lamp socket B projector";

			LampSocketDescriptor desc = new LampSocketDescriptor(name,
					obj.getObj("ClassicLampSocket"),true, LampSocketType.Douille, // LampSocketType
																	// socketType
					10, -90, 90, 0);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		
		{
			subId = 4;

			name = "Robust lamp socket";

			LampSocketDescriptor desc = new LampSocketDescriptor(name,
					obj.getObj("RobustLamp"),true, LampSocketType.Douille, // LampSocketType
																	// socketType
					0, 0, 0, 0);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		
		{
			subId = 8;

			name = "Street light";

			LampSocketDescriptor desc = new LampSocketDescriptor(name,
					obj.getObj("StreetLight"),true, LampSocketType.Douille, // LampSocketType
																	// socketType
					0, 0, 0, 0);
			desc.setPlaceDirection(Direction.YN);
			GhostGroup g = new GhostGroup();
			g.addElement(1, 0, 0);
			g.addElement(2, 0, 0);
			desc.setGhostGroup(g);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}
	void registerLampSupply(int id) {
		int subId, completId;
		String name;

		{
			subId = 0;

			name = "Lamp supply";

			LampSupplyDescriptor desc = new LampSupplyDescriptor(
					name,obj.getObj("lampsupply"),
					32
					);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerDiode(int id) {
		int subId, completId;
		String name;
		IFunction function;
		FunctionTableYProtect baseFunction = new FunctionTableYProtect(
				new double[] { 0.0, 0.01, 0.03, 0.1, 0.2, 0.4, 0.8, 1.2 }, 1.0,
				0, 5);

		{
			subId = 0;

			name = "10A diode";

			function = new FunctionTableYProtect(new double[] { 0.0, 0.1, 0.3,
					1.0, 2.0, 4.0, 8.0, 12.0 }, 1.0, 0, 100);

			DiodeDescriptor desc = new DiodeDescriptor(
					name,// int iconId, String name,
					function,
					10, // double Imax,
					sixNodeThermalLoadInitializer.copy(),
					lowVoltageCableDescriptor

			);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 1;

			name = "25A diode";

			function = new FunctionTableYProtect(new double[] { 0.0, 0.25,
					0.75, 2.5, 5.0, 10.0, 20.0, 30.0 }, 1.0, 0, 100);

			DiodeDescriptor desc = new DiodeDescriptor(
					name,// int iconId, String name,
					function,
					25, // double Imax,
					sixNodeThermalLoadInitializer.copy(),
					lowVoltageCableDescriptor);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 8;

			name = "Signal diode";

			function = baseFunction.duplicate(1.0, 0.1);

			DiodeDescriptor desc = new DiodeDescriptor(name,// int iconId,
															// String name,
					function, 0.1, // double Imax,
					sixNodeThermalLoadInitializer.copy(), signalCableDescriptor

			);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerSwitch(int id) {
		int subId, completId;
		String name;
		IFunction function;
		ElectricalSwitchDescriptor desc;

		{
			subId = 0;

			name = "High voltage switch";

			desc = new ElectricalSwitchDescriptor(name, stdCableRender800V,
					obj.getObj("HighVoltageSwitch"), HVU, HVP, 0.02,// nominalVoltage,
														// nominalPower,
														// nominalDropFactor,
					HVU * 1.5, HVP * 1.2,// maximalVoltage, maximalPower
					cableThermalLoadInitializer.copy(), false);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Low voltage switch";

			desc = new ElectricalSwitchDescriptor(name, stdCableRender50V,
					obj.getObj("LowVoltageSwitch"), LVU, LVP, 0.02,// nominalVoltage,
														// nominalPower,
														// nominalDropFactor,
					LVU * 1.5, LVP * 1.2,// maximalVoltage, maximalPower
					cableThermalLoadInitializer.copy(), false);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 2;

			name = "Medium voltage switch";

			desc = new ElectricalSwitchDescriptor(name, stdCableRender200V,
					obj.getObj("LowVoltageSwitch"), MVU, MVP, 0.02,// nominalVoltage,
														// nominalPower,
														// nominalDropFactor,
					MVU * 1.5, MVP * 1.2,// maximalVoltage, maximalPower
					cableThermalLoadInitializer.copy(), false);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 3;

			name = "Signal voltage switch";

			desc = new ElectricalSwitchDescriptor(name, stdCableRenderSignal,
					obj.getObj("LowVoltageSwitch"), SVU, SVP, 0.02,// nominalVoltage,
														// nominalPower,
														// nominalDropFactor,
					SVU * 1.5, SVP * 1.2,// maximalVoltage, maximalPower
					cableThermalLoadInitializer.copy(), true);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		
		{
			subId = 8;

			name = "Low voltage led button";

			desc = new ElectricalSwitchDescriptor(name, stdCableRenderSignal,
					obj.getObj("ledswitch"), SVU, SVP, 0.02,// nominalVoltage,
														// nominalPower,
														// nominalDropFactor,
					SVU * 1.5, SVP * 1.2,// maximalVoltage, maximalPower
					cableThermalLoadInitializer.copy(), true);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerElectricalBreaker(int id) {
		int subId, completId;
		String name;
		ElectricalBreakerDescriptor desc;

		{
			subId = 0;

			name = "Electrical breaker";

			desc = new ElectricalBreakerDescriptor(name,obj.getObj("HighVoltageSwitch"));

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerElectricalSensor(int id) {
		int subId, completId;
		String name;
		ElectricalSensorDescriptor desc;

		{
			subId = 0;

			name = "Electrical sensor";

			desc = new ElectricalSensorDescriptor(name, "electricalsensor",
					false);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Voltage sensor";

			desc = new ElectricalSensorDescriptor(name, "voltagesensor", true);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerThermalSensor(int id) {
		int subId, completId;
		String name;
		ThermalSensorDescriptor desc;

		{
			subId = 0;

			name = "Thermal sensor";

			desc = new ThermalSensorDescriptor(name,
					obj.getObj("thermalsensor"), false);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Temperature sensor";

			desc = new ThermalSensorDescriptor(name,
					obj.getObj("temperaturesensor"), true);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerElectricalVuMeter(int id) {
		int subId, completId;
		String name;
		ElectricalVuMeterDescriptor desc;
		{
			subId = 0;
			name = "Analog vuMeter";
			desc = new ElectricalVuMeterDescriptor(name, "Vumeter",false);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 8;
			name = "Led vuMeter";
			desc = new ElectricalVuMeterDescriptor(name, "Led",true);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalAlarm(int id) {
		int subId, completId;
		String name;
		ElectricalAlarmDescriptor desc;
		{
			subId = 0;
			name = "Electrical alarm A";
			desc = new ElectricalAlarmDescriptor(name,
					obj.getObj("alarmmedium"), 7, "eln:alarma", 11, 1f);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;
			name = "Electrical alarm B";
			desc = new ElectricalAlarmDescriptor(name,
					obj.getObj("alarmmedium"), 7, "eln:smallalarm_critical",
					1.2, 2f);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalLightSensor(int id) {
		int subId, completId;
		String name;
		ElectricalLightSensorDescriptor desc;
		{
			subId = 0;
			name = "Electrical daylight sensor";
			desc = new ElectricalLightSensorDescriptor(name, obj.getObj("daylightsensor"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalRedstone(int id) {
		int subId, completId;
		String name;
		{
			ElectricalRedstoneInputDescriptor desc;
			subId = 0;
			name = "Redstone to voltage converter";
			desc = new ElectricalRedstoneInputDescriptor(name, obj.getObj("redtoele"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			ElectricalRedstoneOutputDescriptor desc;
			subId = 1;
			name = "Voltage to redstone converter";
			desc = new ElectricalRedstoneOutputDescriptor(name,
					 obj.getObj("eletored"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalGate(int id) {
		int subId, completId;
		String name;
		{
			ElectricalTimeoutDescriptor desc;
			subId = 0;

			name = "Electrical timeout";

			desc = new ElectricalTimeoutDescriptor(name,
					obj.getObj("electricaltimer"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			ElectricalMathDescriptor desc;
			subId = 4;

			name = "Electrical math";

			desc = new ElectricalMathDescriptor(name,
					obj.getObj("PLC"));
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	
	
	void registerWirelessSignal(int id) {
		int subId, completId;
		String name;
		
		{
			WirelessSignalRxDescriptor desc;
			subId = 0;

			name = "Wireless signal receivers";

			desc = new WirelessSignalRxDescriptor(
					name,
					obj.getObj("wirelesssignalrx"),
					false,0
					);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}		
		
		{
			WirelessSignalTxDescriptor desc;
			subId = 8;

			name = "Wireless signal transmitter";

			desc = new WirelessSignalTxDescriptor(
					name,
					obj.getObj("wirelesssignaltx"),
					32
					);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		

		{
			WirelessSignalRxDescriptor desc;
			subId = 16;

			name = "Wireless signal repeater";

			desc = new WirelessSignalRxDescriptor(
					name,
					obj.getObj("wirelesssignalrepeater"),
					true,32
					);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}		
		
	}
	
	void registerElectricalDataLogger(int id) {
		int subId, completId;
		String name;
		{
			ElectricalDataLoggerDescriptor desc;
			subId = 0;

			name = "Data logger";

			desc = new ElectricalDataLoggerDescriptor(name, true,
					"DataloggerCRTFloor", 0f, 1f, 0f);
			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalRelay(int id) {
		int subId, completId;
		String name;
		ElectricalRelayDescriptor desc;

		{
			subId = 0;

			name = "Low voltage relay";

			desc = new ElectricalRelayDescriptor(
					name,obj.getObj("RelayBig"),
					lowVoltageCableDescriptor);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Medium voltage relay";

			desc = new ElectricalRelayDescriptor(
					name,obj.getObj("RelayBig"),
					meduimVoltageCableDescriptor);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 2;

			name = "High voltage relay";

			desc = new ElectricalRelayDescriptor(
					name,obj.getObj("RelayBig"),
					highVoltageCableDescriptor);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerElectricalGateSource(int id) {
		int subId, completId;
		String name;
		ElectricalGateSourceDescriptor desc;

		{
			subId = 0;

			name = "Signal source";

			desc = new ElectricalGateSourceDescriptor(name,obj.getObj("signalsourcepot"),false);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 1;

			name = "Signal source B";

			desc = new ElectricalGateSourceDescriptor(name,obj.getObj("ledswitch"),true);

			sixNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerTransformer(int id) {
		int subId, completId;
		String name;

		{
			subId = 0;
			name = "Transformer";

			TransformerDescriptor desc = new TransformerDescriptor(name,obj.getObj("transformator"),obj.getObj("feromagneticcorea"));
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerHeatFurnace(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Stone heat furnace";

			HeatFurnaceDescriptor desc = new HeatFurnaceDescriptor(name,
					"stonefurnace", 1000,
					Utils.getCoalEnergyReference() * 2 / 3,// double
															// nominalPower,
															// double
															// nominalCombustibleEnergy,
					2, 500,// int combustionChamberMax,double
							// combustionChamberPower,
					new ThermalLoadInitializerByPowerDrop(780, -100, 10, 10) // thermal
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	/*	{
			subId = 1;
			name = "Brick heat furnace";

			HeatFurnaceDescriptor desc = new HeatFurnaceDescriptor(name,
					"stonefurnace", 1500, Utils.getCoalEnergyReference(),// double
																			// nominalPower,
																			// double
																			// nominalCombustibleEnergy,
					2, 750,// int combustionChamberMax,double
							// combustionChamberPower,
					new ThermalLoadInitializerByPowerDrop(780, -100, 10, 10) // thermal
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}*/

	}

	void registerTurbine(int id) {
		int subId, completId;
		String name;

		FunctionTable TtoU = new FunctionTable(new double[] { 0, 0.1, 0.85,
				1.0, 1.1, 1.15, 1.18, 1.19, 1.25 }, 8.0 / 5.0);
		FunctionTable PoutToPin = new FunctionTable(new double[] { 0.0, 0.2,
				0.4, 0.6, 0.8, 1.0, 1.3, 1.8, 2.7 }, 8.0 / 5.0);
/*
		{
			subId = 0;
			name = "Small 50V turbine";
			double RsFactor = 0.25;
			double nominalU = LVU;
			double nominalP = 200;
			double nominalDeltaT = 200;
			TurbineDescriptor desc = new TurbineDescriptor(
					name,
					"turbine50V",
					"Miaouuuu turbine",// int iconId, String name,String
										// description,
					lowVoltageCableDescriptor.render,
					TtoU.duplicate(nominalDeltaT, nominalU),
					PoutToPin.duplicate(nominalP, nominalP), nominalDeltaT,
					nominalU,
					nominalP,
					nominalP / 40,// double nominalDeltaT, double
									// nominalU,nominalP,double nominalPowerLost
					lowVoltageCableDescriptor.electricalRs * RsFactor,
					lowVoltageCableDescriptor.electricalRp,
					lowVoltageCableDescriptor.electricalC / RsFactor,// ElectricalCableDescriptor
																		// electricalCable,
					5.0, nominalDeltaT / 40, // double thermalC,double
												// DeltaTForInput
					nominalP / 2);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}*/
		{
			subId = 1;
			name = "50V turbine";
			double RsFactor = 0.25;
			double nominalU = LVU;
			double nominalP = 300;
			double nominalDeltaT = 250;
			TurbineDescriptor desc = new TurbineDescriptor(
					name,
					"turbine50V",
					"Miaouuuu turbine",// int iconId, String name,String
										// description,
					lowVoltageCableDescriptor.render,
					TtoU.duplicate(nominalDeltaT, nominalU),
					PoutToPin.duplicate(nominalP, nominalP), nominalDeltaT,
					nominalU,
					nominalP,
					nominalP / 40,// double nominalDeltaT, double
									// nominalU,nominalP,double nominalPowerLost
					lowVoltageCableDescriptor.electricalRs * RsFactor,
					lowVoltageCableDescriptor.electricalRp,
					lowVoltageCableDescriptor.electricalC / RsFactor,// ElectricalCableDescriptor
																		// electricalCable,
					5.0, nominalDeltaT / 40, // double thermalC,double
												// DeltaTForInput
					nominalP / 2);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 8;
			name = "200V turbine";
			double RsFactor = 0.25;
			double nominalU = MVU;
			double nominalP = 500;
			double nominalDeltaT = 350;
			TurbineDescriptor desc = new TurbineDescriptor(
					name,
					"turbineb",
					"Miaouuuu turbine",// int iconId, String name,String
										// description,
					meduimVoltageCableDescriptor.render,
					TtoU.duplicate(nominalDeltaT, nominalU),
					PoutToPin.duplicate(nominalP, nominalP), nominalDeltaT,
					nominalU,
					nominalP,
					nominalP / 40,// double nominalDeltaT, double
									// nominalU,nominalP,double nominalPowerLost
					meduimVoltageCableDescriptor.electricalRs * RsFactor,
					meduimVoltageCableDescriptor.electricalRp,
					meduimVoltageCableDescriptor.electricalC / RsFactor,// ElectricalCableDescriptor
																		// electricalCable,
					5.0, nominalDeltaT / 40, // double thermalC,double
												// DeltaTForInput
					nominalP / 2);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		/*
		 * { subId = 1; name = "50V turbine"; double RsFactor = 0.25;
		 * TurbineDescriptor desc = new TurbineDescriptor(
		 * name,"Miaouuuu turbine",//int iconId, String name,String description,
		 * baseTtoU,//FunctionTable TtoU, PinToPout, 300.0,LVU,300,50,//double
		 * nominalDeltaT, double nominalU,nominalP,double nominalPowerLost
		 * lowVoltageCableDescriptor
		 * .electricalRs*RsFactor,lowVoltageCableDescriptor
		 * .electricalRp,lowVoltageCableDescriptor
		 * .electricalC/RsFactor,//ElectricalCableDescriptor electricalCable,
		 * 5.0,15 //double thermalC,double DeltaTForInput );
		 * transparentNodeItem.addDescriptor(subId + (id << 6), desc); }
		 * 
		 * { subId = 2; name = "Upgraded 50V turbine"; double RsFactor = 0.25;
		 * TurbineDescriptor desc = new TurbineDescriptor(
		 * name,"Miaouuuu turbine",//int iconId, String name,String description,
		 * baseTtoU,//FunctionTable TtoU, PinToPout, 400.0,LVU,600,50,//double
		 * nominalDeltaT, double nominalU,nominalP,double nominalPowerLost
		 * lowVoltageCableDescriptor
		 * .electricalRs*RsFactor,lowVoltageCableDescriptor
		 * .electricalRp,lowVoltageCableDescriptor
		 * .electricalC/RsFactor,//ElectricalCableDescriptor electricalCable,
		 * 5.0,20 //double thermalC,double DeltaTForInput );
		 * transparentNodeItem.addDescriptor(subId + (id << 6), desc); }
		 */
	}

	void registerIntelligentTransformer(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "IntelligentTransformer";

			TransparentNodeDescriptor desc = new TransparentNodeDescriptor(
					name, IntelligentTransformerElement.class,
					IntelligentTransformerRender.class);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}

	void registerElectricalFurnace(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Electrical furnace";
			double[] PfTTable = new double[] { 0, 20, 40, 80, 160, 240, 360,
					540, 756, 1058.4, 1481.76 };

			double[] thermalPlostfTTable = new double[PfTTable.length];
			for (int idx = 0; idx < thermalPlostfTTable.length; idx++) {
				thermalPlostfTTable[idx] = PfTTable[idx]
						* Math.pow((idx + 1.0) / thermalPlostfTTable.length, 2)
						* 2;
			}

			FunctionTableYProtect PfT = new FunctionTableYProtect(PfTTable,
					800.0, 0, 100000.0);

			FunctionTableYProtect thermalPlostfT = new FunctionTableYProtect(
					thermalPlostfTTable, 800.0, 0.001, 10000000.0);

			ElectricalFurnaceDescriptor desc = new ElectricalFurnaceDescriptor(
					name, PfT, thermalPlostfT,// thermalPlostfT;
					40// thermalC;
			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	public RecipesList maceratorRecipes = new RecipesList();

	void registerMacerator(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "50V macerator";

			MaceratorDescriptor desc = new MaceratorDescriptor(name,
					"maceratora", LVU, 200,// double nominalU,double nominalP,
					LVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					lowVoltageCableDescriptor,// ElectricalCableDescriptor cable
					maceratorRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 4;
			name = "200V macerator";

			MaceratorDescriptor desc = new MaceratorDescriptor(name,
					"maceratora", MVU, 400,// double nominalU,double nominalP,
					MVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					meduimVoltageCableDescriptor,// ElectricalCableDescriptor
													// cable
					maceratorRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}
/*
	public RecipesList extractorRecipes = new RecipesList();

	void registerExtractor(int id) {

		int subId, completId;
		String name;
		{
			subId = 0;
			name = "50V extractor";

			ElectricalMachineDescriptor desc = new ElectricalMachineDescriptor(
					name,// String name,
					LVU, 200,// double nominalU,double nominalP,
					LVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					lowVoltageCableDescriptor,// ElectricalCableDescriptor cable
					extractorRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 4;
			name = "200V extractor";

			ElectricalMachineDescriptor desc = new ElectricalMachineDescriptor(
					name,// String name,
					MVU, 400,// double nominalU,double nominalP,
					MVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					meduimVoltageCableDescriptor,// ElectricalCableDescriptor
													// cable
					extractorRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}*/

	public RecipesList compressorRecipes = new RecipesList();
	public RecipesList plateMachineRecipes = new RecipesList();

	void registerPlateMachine(int id) {

		int subId, completId;
		String name;
		{
			subId = 0;
			name = "50V plate machine";

			PlateMachineDescriptor desc = new PlateMachineDescriptor(
					name,// String name,
					obj.getObj("platemachinea"),
					LVU, 200,// double nominalU,double nominalP,
					LVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					lowVoltageCableDescriptor,// ElectricalCableDescriptor cable
					plateMachineRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 4;
			name = "200V plate machine";

			PlateMachineDescriptor desc = new PlateMachineDescriptor(
					name,// String name,
					obj.getObj("platemachinea"),
					MVU, 400,// double nominalU,double nominalP,
					MVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					meduimVoltageCableDescriptor,// ElectricalCableDescriptor
													// cable
					plateMachineRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}
	void registerEggIncubator(int id) {

		int subId, completId;
		String name;
		{
			subId = 0;
			name = "50V Egg incubator";

			EggIncubatorDescriptor desc = new EggIncubatorDescriptor(
					name, obj.getObj("eggincubator"), 
					lowVoltageCableDescriptor,
					LVU,50	);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}


	}
	
	
	void registerCompressor(int id) {

		int subId, completId;
		String name;
		{
			subId = 0;
			name = "50V compressor";

			CompressorDescriptor desc = new CompressorDescriptor(
					name,// String name,
					obj.getObj("compressora"),
					LVU, 200,// double nominalU,double nominalP,
					LVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					lowVoltageCableDescriptor,// ElectricalCableDescriptor cable
					compressorRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 4;
			name = "200V compressor";

			CompressorDescriptor desc = new CompressorDescriptor(
					name,// String name,
					obj.getObj("compressora"),
					MVU, 400,// double nominalU,double nominalP,
					MVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					meduimVoltageCableDescriptor,// ElectricalCableDescriptor
													// cable
					compressorRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}
	
		
	

	public RecipesList magnetiserRecipes = new RecipesList();

	void registermagnetiser(int id) {

		int subId, completId;
		String name;
		{
			subId = 0;
			name = "50V magnetizer";

			MagnetizerDescriptor desc = new MagnetizerDescriptor(
					name,// String name,
					obj.getObj("magnetizera"),
					LVU, 200,// double nominalU,double nominalP,
					LVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					lowVoltageCableDescriptor,// ElectricalCableDescriptor cable
					magnetiserRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 4;
			name = "200V magnetizer";

			MagnetizerDescriptor desc = new MagnetizerDescriptor(
					name,// String name,
					obj.getObj("magnetizera"),
					MVU, 400,// double nominalU,double nominalP,
					MVU * 1.25,// double maximalU,
					new ThermalLoadInitializer(80, -100, 10, 100000.0),// thermal,
					meduimVoltageCableDescriptor,// ElectricalCableDescriptor
													// cable
					magnetiserRecipes);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerSolarPannel(int id) {
		int subId, completId;
		GhostGroup ghostGroup;
		String name;

		FunctionTable diodeIfUBase;
		diodeIfUBase = new FunctionTableYProtect(new double[] { 0.0, 0.002,
				0.005, 0.01, 0.015, 0.02, 0.025, 0.03, 0.035, 0.04, 0.045,
				0.05, 0.06, 0.07, 0.08, 0.09, 0.10, 0.11, 0.12, 0.13, 1.0 },
				1.0, 0, 1.0);
		/*
		 * { double[] table = new double[81]; for(int idx = 0;idx <
		 * table.length;idx++) { table[idx] = 0.1*idx/table.length; }
		 * table[table.length-1] = 1.0;
		 * 
		 * diodeIfUBase = new FunctionTableYProtect(table, 1.0, 0, 1.0); }
		 */
		FunctionTable solarIfSBase;
		solarIfSBase = new FunctionTable(new double[] { 0.0, 0.1, 0.4, 0.6,
				0.8, 1.0 }, 1);

		double LVSolarU = 59;
		
		/*
		{
			subId = 0;
			name = "Test solar pannel";

			ghostGroup = new GhostGroup();
			ghostGroup.addElement(0, 1, 0);
			ghostGroup.addElement(0, 2, 0);

			SolarPannelDescriptor desc = new SolarPannelDescriptor(name,// iconID,Name
					ghostGroup, 0, 2, 0, // ghost,int solarOffsetX,int
											// solarOffsetY,int solarOffsetZ,
					diodeIfUBase,// FunctionTable diodeIfUBase,
					solarIfSBase,// solarIfS
					LVSolarU, 500 / LVU,// double electricalUmax,double
										// electricalImax,
					0.05,// ,double electricalDropFactor
					Math.PI / 4, Math.PI / 4 * 3 // alphaMin alphaMax
			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}*/
		{
			subId = 1;
			name = "Small solar pannel";

			ghostGroup = new GhostGroup();



			SolarPannelDescriptor desc = new SolarPannelDescriptor(name,// String
																		// name,
					obj.getObj("smallsolarpannel"),null,
					ghostGroup, 0, 1, 0,// GhostGroup ghostGroup, int
										// solarOffsetX,int solarOffsetY,int
										// solarOffsetZ,
					// FunctionTable solarIfSBase,
					LVSolarU / 4, 65.0,// double electricalUmax,double
										// electricalPmax,
					0.01,// ,double electricalDropFactor
					Math.PI / 2, Math.PI / 2 // alphaMin alphaMax
			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{
			subId = 2;
			name = "Small rotating solar pannel";

			ghostGroup = new GhostGroup();
			/*
			 * SolarPannelDescriptor desc = new SolarPannelDescriptor(
			 * name,//iconID,Name ghostGroup,0,1,0, //ghost,int solarOffsetX,int
			 * solarOffsetY,int solarOffsetZ, diodeIfUBase,//FunctionTable
			 * diodeIfUBase, solarIfSBase,//solarIfS LVSolarU/4,100 / LVU *
			 * 4,//double electricalUmax,double electricalImax, 0.05,//,double
			 * electricalDropFactor Math.PI/4,Math.PI/4*3 //alphaMin alphaMax );
			 */

			SolarPannelDescriptor desc = new SolarPannelDescriptor(name,// String
																		// name,
					obj.getObj("smallsolarpannelrot"),lowVoltageCableDescriptor.render,
					ghostGroup, 0, 1, 0,// GhostGroup ghostGroup, int
										// solarOffsetX,int solarOffsetY,int
										// solarOffsetZ,
					// FunctionTable solarIfSBase,
					LVSolarU / 4, 65.0,// double electricalUmax,double
										// electricalPmax,
					0.01,// ,double electricalDropFactor
					Math.PI / 4, Math.PI / 4 * 3 // alphaMin alphaMax
			);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerHeatingCorp(int id) {
		int subId, completId;
		String name;

		HeatingCorpElement element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("Small 50V copper heating corp",// iconId,
																				// name,
					LVU, 150,// electricalNominalU, electricalNominalP,
					190,// electricalMaximalP)
					lowVoltageCableDescriptor// ElectricalCableDescriptor
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("50V copper heating corp",// iconId,
																		// name,
					LVU, 250,// electricalNominalU, electricalNominalP,
					320,// electricalMaximalP)
					lowVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 2;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("Small 200V copper heating corp",// iconId,
																				// name,
					MVU, 400,// electricalNominalU, electricalNominalP,
					500,// electricalMaximalP)
					meduimVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 3;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("200V copper heating corp",// iconId,
																		// name,
					MVU, 600,// electricalNominalU, electricalNominalP,
					750,// electricalMaximalP)
					highVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 4;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("Small 50V iron heating corp",// iconId,
																			// name,
					LVU, 180,// electricalNominalU, electricalNominalP,
					225,// electricalMaximalP)
					lowVoltageCableDescriptor// ElectricalCableDescriptor
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 5;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("50V iron heating corp",// iconId,
																		// name,
					LVU, 375,// electricalNominalU, electricalNominalP,
					480,// electricalMaximalP)
					lowVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 6;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("Small 200V iron heating corp",// iconId,
																			// name,
					MVU, 600,// electricalNominalU, electricalNominalP,
					750,// electricalMaximalP)
					meduimVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 7;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("200V iron heating corp",// iconId,
																		// name,
					MVU, 900,// electricalNominalU, electricalNominalP,
					1050,// electricalMaximalP)
					highVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 8;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("Small 50V tungsten heating corp",// iconId,
																				// name,
					LVU, 240,// electricalNominalU, electricalNominalP,
					300,// electricalMaximalP)
					lowVoltageCableDescriptor// ElectricalCableDescriptor
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 9;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("50V tungsten heating corp",// iconId,
																			// name,
					LVU, 500,// electricalNominalU, electricalNominalP,
					640,// electricalMaximalP)
					lowVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 10;
			completId = subId + (id << 6);
			element = new HeatingCorpElement(
					"Small 200V tungsten heating corp",// iconId, name,
					MVU, 800,// electricalNominalU, electricalNominalP,
					1000,// electricalMaximalP)
					meduimVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 11;
			completId = subId + (id << 6);
			element = new HeatingCorpElement("200V tungsten heating corp",// iconId,
																			// name,
					MVU, 1200,// electricalNominalU, electricalNominalP,
					1500,// electricalMaximalP)
					highVoltageCableDescriptor);
			sharedItem.addElement(completId, element);
		}

	}

	void registerThermalIsolator(int id) {
		int subId, completId;
		String name;
/*
		ThermalIsolatorElement element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new ThermalIsolatorElement("Sand thermal isolator",// iconId,
																			// name,
					0.7, 1000);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			element = new ThermalIsolatorElement("Stone thermal isolator",// iconId,
																			// name,
					0.8, 1000);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 2;
			completId = subId + (id << 6);
			element = new ThermalIsolatorElement("Brick thermal isolator",// iconId,
																			// name,
					0.5, 1000);
			sharedItem.addElement(completId, element);
		}*/
		/*
		 * { subId = 3; completId = subId + (id << 6); element = new
		 * ThermalIsolatorElement( "Wood thermal isolator",//iconId, name,
		 * 0.05,100 ); sharedItem.addElement(completId, element); }
		 */

	}

	void registerRegulatorItem(int id) {
		int subId, completId;
		String name;
		IRegulatorDescriptor element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new RegulatorOnOffDescriptor("On/OFF regulator 1%",
					"onoffregulator", 0.01);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			element = new RegulatorOnOffDescriptor("On/OFF regulator 10%",
					"onoffregulator", 0.1);
			sharedItem.addElement(completId, element);
		}

		{
			subId = 8;
			completId = subId + (id << 6);
			element = new RegulatorAnalogDescriptor("Analogic regulator",
					"Analogicregulator");
			sharedItem.addElement(completId, element);
		}/*
		 * { subId = 9; completId = subId + (id << 6); element = new
		 * RegulatorAnalogDescriptor( "Analogic PI regulator" );
		 * sharedItem.addElement(completId, element); } { subId = 10; completId
		 * = subId + (id << 6); element = new RegulatorAnalogDescriptor(
		 * "Analogic PID regulator" ); sharedItem.addElement(completId,
		 * element); }
		 */

	}

	void registerLampItem(int id) {
		int subId, completId;
		String name;
		double incondecentLife = Utils.minecraftDay * 10;
		double[] lightPower = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				15, 20, 30, 40, 60 };
		double[] lightLevel = new double[16];
		double economicPowerFactor = 0.75;
		double economicLife = incondecentLife * 4;
		double standardGrowRate = 0.0;
		for (int idx = 0; idx < 16; idx++) {
			lightLevel[idx] = (idx + 0.49) / 15.0;
		}
		LampDescriptor element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new LampDescriptor("Small 50V incandescent light bulb",
					"incandescentlampiron", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, LVU, lightPower[12], // nominalU,
																	// nominalP
					lightLevel[12], incondecentLife,standardGrowRate // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			element = new LampDescriptor("50V incandescent light bulb",
					"incandescentlampiron", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, LVU, lightPower[14], // nominalU,
																	// nominalP
					lightLevel[14], incondecentLife,standardGrowRate // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 2;
			completId = subId + (id << 6);
			element = new LampDescriptor("200V incandescent light bulb",
					"incandescentlampiron", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, MVU, lightPower[14], // nominalU,
																	// nominalP
					lightLevel[14], incondecentLife,standardGrowRate // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		/*
		 * { subId = 3; completId = subId + (id << 6); element = new
		 * LampDescriptor( "400V incandescent light bulb",
		 * LampDescriptor.Type.Incandescent,LampSocketType.Tube, 400
		 * ,lightPower[14], //nominalU, nominalP lightLevel[14],incondecentLife
		 * //nominalLight, nominalLife ); sharedItem.addElement(completId,
		 * element); }
		 */
		{
			subId = 4;
			completId = subId + (id << 6);
			element = new LampDescriptor(
					"Small 50V carbon incandescent light bulb",
					"incandescentlampcarbon", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, LVU, lightPower[11], // nominalU,
																	// nominalP
					lightLevel[11], incondecentLife / 3,standardGrowRate // nominalLight,
														// nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 5;
			completId = subId + (id << 6);
			element = new LampDescriptor("50V carbon incandescent light bulb",
					"incandescentlampcarbon", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, LVU, lightPower[13], // nominalU,
																	// nominalP
					lightLevel[13], incondecentLife / 3,standardGrowRate // nominalLight,
														// nominalLife
			);
			sharedItem.addElement(completId, element);
		}

		{
			subId = 16;
			completId = subId + (id << 6);
			element = new LampDescriptor("Small 50V economic light bulb",
					"economiclamp", LampDescriptor.Type.eco,
					LampSocketType.Douille, LVU, lightPower[12]
							* economicPowerFactor, // nominalU, nominalP
					lightLevel[12], economicLife,standardGrowRate // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 17;
			completId = subId + (id << 6);
			element = new LampDescriptor("50V economic light bulb",
					"economiclamp", LampDescriptor.Type.eco,
					LampSocketType.Douille, LVU, lightPower[14]
							* economicPowerFactor, // nominalU, nominalP
					lightLevel[14], economicLife,standardGrowRate // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 18;
			completId = subId + (id << 6);
			element = new LampDescriptor("200V economic light bulb",
					"economiclamp", LampDescriptor.Type.eco,
					LampSocketType.Douille, MVU, lightPower[14]
							* economicPowerFactor, // nominalU, nominalP
					lightLevel[14], economicLife,standardGrowRate // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		
		{
			subId = 32;
			completId = subId + (id << 6);
			element = new LampDescriptor("50V farming lamp",
					"incandescentlampiron", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, LVU, 120, // nominalU, nominalP
					lightLevel[15], incondecentLife,0.50 // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 36;
			completId = subId + (id << 6);
			element = new LampDescriptor("200V farming lamp",
					"incandescentlampiron", LampDescriptor.Type.Incandescent,
					LampSocketType.Douille, MVU, 120, // nominalU, nominalP
					lightLevel[15], incondecentLife,0.50 // nominalLight, nominalLife
			);
			sharedItem.addElement(completId, element);
		}

	}

	void registerProtection(int id) {
		int subId, completId;
		String name;

		{
			OverHeatingProtectionDescriptor element;
			subId = 0;
			completId = subId + (id << 6);
			element = new OverHeatingProtectionDescriptor(
					"OverHeating protection");
			sharedItem.addElement(completId, element);
		}
		{
			OverVoltageProtectionDescriptor element;
			subId = 1;
			completId = subId + (id << 6);
			element = new OverVoltageProtectionDescriptor(
					"OverVoltage protection");
			sharedItem.addElement(completId, element);
		}

	}

	void registerCombustionChamber(int id) {
		int subId, completId;
		String name;
		{
			CombustionChamber element;
			subId = 0;
			completId = subId + (id << 6);
			element = new CombustionChamber("Combustion chamber");
			sharedItem.addElement(completId, element);
		}

	}

	void registerFerromagneticCore(int id) {
		int subId, completId;
		String name;

		FerromagneticCoreDescriptor element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new FerromagneticCoreDescriptor(
					"Cheap ferromagnetic core",obj.getObj("feromagneticcorea"),// iconId, name,
					10);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			element = new FerromagneticCoreDescriptor(
					"Average ferromagnetic core",obj.getObj("feromagneticcorea"),// iconId, name,
					4);
			sharedItem.addElement(completId, element);
		}
		{
			subId = 2;
			completId = subId + (id << 6);
			element = new FerromagneticCoreDescriptor(
					"Optimal ferromagnetic core",obj.getObj("feromagneticcorea"),// iconId, name,
					1);
			sharedItem.addElement(completId, element);
		}
	}

	public static OreDescriptor oreTin, oreCopper, oreSilver;

	void registerOre() {
		int id;
		String name;
		/*{
			id = 0;
			name = "Tin ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					200, 3, 9, 0, 80 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax

			);
			oreTin = desc;
			oreItem.addDescriptor(id, desc);

		}*/
		{
			id = 1;

			name = "Copper ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					30, 6, 10, 0, 80 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax
			);
			oreCopper = desc;
			oreItem.addDescriptor(id, desc);
		}
		/*{
			id = 2;

			name = "Silver ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					200, 3, 9, 0, 80 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax
			);
			oreSilver = desc;
			oreItem.addDescriptor(id, desc);
		}*/
	/*{
			id = 3;

			name = "Aluminum ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					200, 3, 9, 0, 80 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax
			);
			oreItem.addDescriptor(id, desc);
		}*/
		{
			id = 4;

			name = "Plumb ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					8, 3, 9, 0, 24 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax
			);
			oreItem.addDescriptor(id, desc);
		}
		{
			id = 5;

			name = "Tungsten ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					4, 3, 9, 0, 32 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax
			);
			oreItem.addDescriptor(id, desc);
		}
		{
			id = 6;

			name = "Cinnabar ore";

			OreDescriptor desc = new OreDescriptor(name, id, // int itemIconId,
																// String
																// name,int
																// metadata,
					3, 3, 9, 0, 32 // int spawnRate,int spawnSizeMin,int
										// spawnSizeMax,int spawnHeightMin,int
										// spawnHeightMax
			);
			oreItem.addDescriptor(id, desc);
		}

	}

	public static GenericItemUsingDamageDescriptorWithComment dustTin,
			dustCopper, dustSilver;

	void registerDust(int id) {
		int subId, completId;
		String name;
		GenericItemUsingDamageDescriptorWithComment element;

	/*	{
			subId = 0;
			completId = subId + (id << 6);

			name = "Tin dust";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			dustTin = element;
			sharedItem.addElement(completId, element);
		}*/
		{
			subId = 1;
			completId = subId + (id << 6);

			name = "Copper dust";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			dustCopper = element;
			sharedItem.addElement(completId, element);
		}
		{
			subId = 2;
			completId = subId + (id << 6);

			name = "Iron dust";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			dustCopper = element;
			sharedItem.addElement(completId, element);
		}

		{
			id = 5;

			name = "Plumb dust";

			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			sharedItem.addElement(id, element);
		}
		{
			id = 6;

			name = "Tungsten dust";

			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			sharedItem.addElement(id, element);
		}
		
		{ 
			id = 7;
		  
		  name = "Gold dust";
		  
		  element = new GenericItemUsingDamageDescriptorWithComment(
				  name, new String[]{"dudu dust","miaou"} );
				  sharedItem.addElement(id, element); 
		}
		 

		{
			id = 8;

			name = "Coal dust";

			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			sharedItem.addElement(id, element);
		}
		{
			id = 9;

			name = "Steel dust";

			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			sharedItem.addElement(id, element);
		}

		{
			id = 10;

			name = "Cinnabar dust";

			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			sharedItem.addElement(id, element);
		}
		{
			id = 11;

			name = "Purified cinnabar dust";

			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "dudu dust", "miaou" });
			sharedItem.addElement(id, element);
		}

	}

	GenericItemUsingDamageDescriptorWithComment tinIngot, copperIngot,
			silverIngot, plumbIngot, tungstenIngot;

	void registerIngot(int id) {
		int subId, completId;
		String name;

		GenericItemUsingDamageDescriptorWithComment element;

	/*	{
			subId = 0;
			completId = subId + (id << 6);

			name = "Tin ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));
			tinIngot = element;
		}*/
		{
			subId = 1;
			completId = subId + (id << 6);

			name = "Copper ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless^2", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));
			copperIngot = element;
		}
	/*	{
			subId = 2;
			completId = subId + (id << 6);

			name = "Silver ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless^3", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));
			silverIngot = element;
		}
		{
			subId = 3;
			completId = subId + (id << 6);

			name = "Aluminum ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless^4", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));

		}*/
		{
			subId = 4;
			completId = subId + (id << 6);

			name = "Plumb ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));
			plumbIngot = element;
		}

		{
			subId = 5;
			completId = subId + (id << 6);

			name = "Tungsten ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));
			tungstenIngot = element;
		}

		{
			subId = 6;
			completId = subId + (id << 6);

			name = "Ferrite ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));

		}

		{
			subId = 7;
			completId = subId + (id << 6);

			name = "Steel ingot";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));

		}

		{
			subId = 8;
			completId = subId + (id << 6);

			name = "Mercury";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));

		}
	}

	void registerElectricalMotor(int id) {

		int subId, completId;
		String name;
		GenericItemUsingDamageDescriptorWithComment element;

		{
			subId = 0;
			completId = subId + (id << 6);

			name = "Electrical motor";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));

		}
		{
			subId = 1;
			completId = subId + (id << 6);

			name = "Advanced electrical motor";
			element = new GenericItemUsingDamageDescriptorWithComment(name,// iconId,
																			// name,
					new String[] { "useless", "miaou" });
			sharedItem.addElement(completId, element);
			// GameRegistry.registerCustomItemStack(name,
			// element.newItemStack(1));

		}

		/*
		 * ElectricalMotorDescriptor element; { subId = 0; completId = subId +
		 * (id << 6); element = new ElectricalMotorDescriptor(
		 * "Small 50V electrical motor",//iconId, name, LVU,240,//double
		 * nominalU,double nominalP, LVU*1.2,//double maximalU,
		 * 1000000.0,200,20,//double thermalConductivityTao,double
		 * thermalWarmLimit,double thermalHeatTime, lowVoltageCableDescriptor
		 * 
		 * ); sharedItem.addElement(completId, element); }
		 * 
		 * { subId = 1; completId = subId + (id << 6); element = new
		 * ElectricalMotorDescriptor( "50V electrical motor",//iconId, name,
		 * LVU,500,//double nominalU,double nominalP, LVU*1.2,//double maximalU,
		 * 1000000.0,200,20,//double thermalConductivityTao,double
		 * thermalWarmLimit,double thermalHeatTime, lowVoltageCableDescriptor
		 * 
		 * ); sharedItem.addElement(completId, element); }
		 * 
		 * { subId = 2; completId = subId + (id << 6); element = new
		 * ElectricalMotorDescriptor( "200V electrical motor",//iconId, name,
		 * 200,1000,//double nominalU,double nominalP, LVU*1.2,//double maximaU,
		 * 1000000.0,200,20,//double thermalConductivityTao,double
		 * thermalWarmLimit,double thermalHeatTime, meduimVoltageCableDescriptor
		 * 
		 * ); sharedItem.addElement(completId, element); }
		 */

	}


	private void registerArmor() {
		ItemStack stack;
		String name;

		{
			name = "Copper helmet";
			helmetCopper = (ItemArmor)(new genericArmorItem(helmetCopperId, EnumArmorMaterial.IRON, 2,0,"eln:textures/armor/copper_layer_1.png","eln:textures/armor/copper_layer_2.png")).setUnlocalizedName(name).func_111206_d("eln:copper_helmet").setCreativeTab(creativeTab);
			stack = new ItemStack(helmetCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}
		{
			name = "Copper plate";
			plateCopper = (ItemArmor)(new genericArmorItem(plateCopperId, EnumArmorMaterial.IRON, 2,1,"eln:textures/armor/copper_layer_1.png","eln:textures/armor/copper_layer_2.png")).setUnlocalizedName(name).func_111206_d("eln:copper_chestplate").setCreativeTab(creativeTab);
			stack = new ItemStack(plateCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}
		{
			name = "Copper legs";
			legsCopper = (ItemArmor)(new genericArmorItem(legsCopperId, EnumArmorMaterial.IRON, 2,2,"eln:textures/armor/copper_layer_1.png","eln:textures/armor/copper_layer_2.png")).setUnlocalizedName(name).func_111206_d("eln:copper_leggings").setCreativeTab(creativeTab);
			stack = new ItemStack(legsCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}
		{
			name = "Copper boots";
			bootsCopper = (ItemArmor)(new genericArmorItem(bootsCopperId, EnumArmorMaterial.IRON, 2,3,"eln:textures/armor/copper_layer_1.png","eln:textures/armor/copper_layer_2.png")).setUnlocalizedName(name).func_111206_d("eln:copper_boots").setCreativeTab(creativeTab);
			stack = new ItemStack(bootsCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}
		
		int armorPoint;
		String t1,t2;
		t1 = "eln:textures/armor/ecoal_layer_1.png";
		t2 = "eln:textures/armor/ecoal_layer_2.png";
		double energyPerDamage = 500;
		int armor,armorMarge;
		EnumArmorMaterial eCoalMaterial = EnumHelper.addArmorMaterial("ECoal", 10, new int[]{2, 6, 5, 2}, 9);
		{
			name = "ECoal helmet";
			armor = 2;
			armorMarge = 1;
			helmetECoal = (ItemArmor)(new ElectricalArmor(helmetECoalId, eCoalMaterial, 2,0,t1,t2,
										(armor + armorMarge)*energyPerDamage,250.0,//double energyStorage,double chargePower
										armor/20.0,armor*energyPerDamage,//double ratioMax,double ratioMaxEnergy,
										energyPerDamage//double energyPerDamage										
										)).setUnlocalizedName(name).func_111206_d("eln:ecoal_helmet").setCreativeTab(creativeTab);
			stack = new ItemStack(helmetECoal);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}	
		{
			name = "ECoal plate";
			armor = 6;
			armorMarge = 2;
			plateECoal = (ItemArmor)(new ElectricalArmor(plateECoalId, eCoalMaterial, 2,1,t1,t2,
										(armor + armorMarge)*energyPerDamage,250.0,//double energyStorage,double chargePower
										armor/20.0,armor*energyPerDamage,//double ratioMax,double ratioMaxEnergy,
										energyPerDamage//double energyPerDamage										
										)).setUnlocalizedName(name).func_111206_d("eln:ecoal_chestplate").setCreativeTab(creativeTab);
			stack = new ItemStack(plateECoal);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}		
		{
			name = "ECoal legs";
			armor = 5;
			armorMarge = 2;
			legsECoal = (ItemArmor)(new ElectricalArmor(legsECoalId, eCoalMaterial, 2,2,t1,t2,
										(armor + armorMarge)*energyPerDamage,250.0,//double energyStorage,double chargePower
										armor/20.0,armor*energyPerDamage,//double ratioMax,double ratioMaxEnergy,
										energyPerDamage//double energyPerDamage										
										)).setUnlocalizedName(name).func_111206_d("eln:ecoal_leggings").setCreativeTab(creativeTab);
			stack = new ItemStack(legsECoal);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}
		{
			name = "ECoal boots";
			armor = 2;
			armorMarge = 1;
			bootsECoal = (ItemArmor)(new ElectricalArmor(bootsECoalId, eCoalMaterial, 2,3,t1,t2,
										(armor + armorMarge)*energyPerDamage,250.0,//double energyStorage,double chargePower
										armor/20.0,armor*energyPerDamage,//double ratioMax,double ratioMaxEnergy,
										energyPerDamage//double energyPerDamage										
										)).setUnlocalizedName(name).func_111206_d("eln:ecoal_boots").setCreativeTab(creativeTab);
			stack = new ItemStack(bootsECoal);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}	
	}

	
	private void registerTool(){
		ItemStack stack;
		String name;
		{
			name = "Copper sword";
			swordCopper = (new ItemSword(swordCopperId, EnumToolMaterial.IRON)).setUnlocalizedName(name).func_111206_d("eln:copper_sword");
			stack = new ItemStack(swordCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}		
		{
			name = "Copper hoe";
			hoeCopper = (new ItemHoe(hoeCopperId, EnumToolMaterial.IRON)).setUnlocalizedName(name).func_111206_d("eln:copper_hoe");
			stack = new ItemStack(hoeCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}		
		{
			name = "Copper shovel";
			shovelCopper = (new ItemSpade(shovelCopperId, EnumToolMaterial.IRON)).setUnlocalizedName(name).func_111206_d("eln:copper_shovel");
			stack = new ItemStack(shovelCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}		
		{
			name = "Copper pickaxe";
			pickaxeCopper = (new ItemPickaxe(pickaxeCopperId, EnumToolMaterial.IRON)).setUnlocalizedName(name).func_111206_d("eln:copper_pickaxe");
			stack = new ItemStack(pickaxeCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}		
		{
			name = "Copper axe";
			axeCopper = (new ItemAxe(axeCopperId, EnumToolMaterial.IRON)).setUnlocalizedName(name).func_111206_d("eln:copper_axe");
			stack = new ItemStack(axeCopper);
			LanguageRegistry.addName(stack,name);
			GameRegistry.registerCustomItemStack(name, stack.copy());
		}		
	

	}
	//public static int swordCopperId,hoeCopperId,shovelCopperId,pickaxeCopperId,axeCopperId;
	//public static Item swordCopper,hoeCopper,shovelCopper,pickaxeCopper,axeCopper;
	
	void registerSolarTracker(int id) {
		int subId, completId;
		String name;

		SolarTrackerDescriptor element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new SolarTrackerDescriptor("Solar tracker"// iconId, name,

			);
			sharedItem.addElement(completId, element);
		}

	}

	void registerWindTurbine(int id) {
		int subId, completId;
		String name;
		
		FunctionTable PfW = new FunctionTable(
				new double[] { 0.0, 0.05,0.2, 0.5, 0.8, 1.0, 1.1, 1.15, 1.2 },
				8.0 / 5.0);
		{
			subId = 0;
			name = "Wind turbine";

			WindTurbineDescriptor desc = new WindTurbineDescriptor(
					name,obj.getObj("WindTurbineMini"),   //name,Obj3D obj,
					lowVoltageCableDescriptor,//ElectricalCableDescriptor cable,
					PfW,//PfW
					200,10,//double nominalPower,double nominalWind,
					LVU*1.18,22,//double maxVoltage, double maxWind,
					3,//int offY,
					7,2,2,//int rayX,int rayY,int rayZ,
					2,0.07//int blockMalusMinCount,double blockMalus
					
					);

			GhostGroup g = new GhostGroup();
			g.addElement(0, 1, 0);
			g.addRectangle(0, 0, 1, 3, -1, 1);
			desc.setGhostGroup(g);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerThermalDissipatorPassiveAndActive(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Small passive thermal dissipator";

			ThermalDissipatorPassiveDescriptor desc = new ThermalDissipatorPassiveDescriptor(
					name,
					obj.getObj("passivethermaldissipatora"),
					200, -100,// double warmLimit,double coolLimit,
					250, 30,// double nominalP,double nominalT,
					10, 4// double nominalTao,double nominalConnectionDrop

			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{
			subId = 32;
			name = "Small active thermal dissipator";

			ThermalDissipatorActiveDescriptor desc = new ThermalDissipatorActiveDescriptor(
					name, 
					obj.getObj("activethermaldissipatora"),
					LVU, 50,// double nominalElectricalU,double
									// electricalNominalP,
					800,// double nominalElectricalCoolingPower,
					lowVoltageCableDescriptor,// ElectricalCableDescriptor
												// cableDescriptor,
					130, -100,// double warmLimit,double coolLimit,
					200, 30,// double nominalP,double nominalT,
					10, 4// double nominalTao,double nominalConnectionDrop

			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

	}
	
	
	void registerTransparentNodeMisc(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Experimental transporter";

			Coordonate[] powerLoad = new Coordonate[2];
			powerLoad[0] = new Coordonate(-1,0,1,0);
			powerLoad[1] = new Coordonate(-1,0,-1,0);
			
			GhostGroup doorOpen = new GhostGroup();
			doorOpen.addRectangle(-4, -3, 2, 2, 0, 0);
			
			GhostGroup doorClose = new GhostGroup();
			doorClose.addRectangle(-2, -2, 0, 1, 0, 0);

			
			TeleporterDescriptor desc = new TeleporterDescriptor(
					name, obj.getObj("Transporter"),
					highVoltageCableDescriptor,
					new Coordonate(-1,0,0,0),new Coordonate(-1,1,0,0),
					2,//int areaH	
					powerLoad,
					doorOpen,doorClose

			);

			GhostGroup g = new GhostGroup();
			g.addRectangle(-2, 0, 0, 2, -1, -1);
			g.addRectangle(-2, 0, 0, 2, 1, 1);
			g.addRectangle(-4, 0, 2, 2, 0, 0);
			g.addElement(0, 1, 0);
			g.addRectangle(-3, -3, 0, 1, -1, -1);
			g.addRectangle(-3, -3, 0, 1 , 1, 1);
			//g.addElement(-4, 0, -1);
			//g.addElement(-4, 0, 1);
			
			desc.setGhostGroup(g);
			
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}		
		
	}


	void registerMppt(int id) {
		int subId, completId;
		String name;
		MpptDescriptor desc;

		FunctionTable PoutfPin = new FunctionTable(new double[] { 0.0, 0.1,
				0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.08, 1.15, 1.21,
				1.26, 1.29 }, 1.5);

		{
			subId = 0;
			name = "Basic maximum power point tracker";

			desc = new MpptDescriptor(name, -1, LVU * 1.3,// double
															// inUmin,double
															// inUmax,
					10, LVU * 1.19,// double outUmin,double outUmax,

					500,// double designedPout,
					PoutfPin,// FunctionTable PoutfPin,
					0.01,// electricalLoadDropFactor

					6.0, 0.1,// double inResistorLowHighTime,double
								// inResistorNormalTime,
					0.05,// double inResistorStepFactor,
					1.0, 50.0// double inResistorMin,double inResistorMax

			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerElectricalAntenna(int id) {
		int subId, completId;
		String name;
		{

			subId = 0;
			ElectricalAntennaTxDescriptor desc;
			name = "Low power transmitter antenna";
			double P = 250;
			desc = new ElectricalAntennaTxDescriptor(name,
					obj.getObj("lowpowertransmitterantenna"), 200,// int
																	// rangeMax,
					0.9, 0.7,// double electricalPowerRatioEffStart,double
								// electricalPowerRatioEffEnd,
					LVU, P,// double electricalNominalVoltage,double
							// electricalNominalPower,
					LVU * 1.3, P * 1.3,// electricalMaximalVoltage,double
										// electricalMaximalPower,
					lowVoltageCableDescriptor);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{

			subId = 1;
			ElectricalAntennaRxDescriptor desc;
			name = "Low power receiver antenna";
			double P = 250;
			desc = new ElectricalAntennaRxDescriptor(name,
					obj.getObj("lowpowerreceiverantenna"), LVU, P,// double
																	// electricalNominalVoltage,double
																	// electricalNominalPower,
					LVU * 1.3, P * 1.3,// electricalMaximalVoltage,double
										// electricalMaximalPower,
					lowVoltageCableDescriptor);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{

			subId = 2;
			ElectricalAntennaTxDescriptor desc;
			name = "Medium power transmitter antenna";
			double P = 1000;
			desc = new ElectricalAntennaTxDescriptor(name,
					obj.getObj("lowpowertransmitterantenna"), 250,// int
																	// rangeMax,
					0.9, 0.75,// double electricalPowerRatioEffStart,double
								// electricalPowerRatioEffEnd,
					MVU, P,// double electricalNominalVoltage,double
							// electricalNominalPower,
					MVU * 1.3, P * 1.3,// electricalMaximalVoltage,double
										// electricalMaximalPower,
					meduimVoltageCableDescriptor);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{

			subId = 3;
			ElectricalAntennaRxDescriptor desc;
			name = "Medium power receiver antenna";
			double P = 1000;
			desc = new ElectricalAntennaRxDescriptor(name,
					obj.getObj("lowpowerreceiverantenna"), MVU, P,// double
																	// electricalNominalVoltage,double
																	// electricalNominalPower,
					MVU * 1.3, P * 1.3,// electricalMaximalVoltage,double
										// electricalMaximalPower,
					meduimVoltageCableDescriptor);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}

		{

			subId = 4;
			ElectricalAntennaTxDescriptor desc;
			name = "High power transmitter antenna";
			double P = 2000;
			desc = new ElectricalAntennaTxDescriptor(name,
					obj.getObj("lowpowertransmitterantenna"), 300,// int
																	// rangeMax,
					0.95, 0.8,// double electricalPowerRatioEffStart,double
								// electricalPowerRatioEffEnd,
					HVU, P,// double electricalNominalVoltage,double
							// electricalNominalPower,
					HVU * 1.3, P * 1.3,// electricalMaximalVoltage,double
										// electricalMaximalPower,
					highVoltageCableDescriptor);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
		{

			subId = 5;
			ElectricalAntennaRxDescriptor desc;
			name = "High power receiver antenna";
			double P = 2000;
			desc = new ElectricalAntennaRxDescriptor(name,
					obj.getObj("lowpowerreceiverantenna"), HVU, P,// double
																	// electricalNominalVoltage,double
																	// electricalNominalPower,
					HVU * 1.3, P * 1.3,// electricalMaximalVoltage,double
										// electricalMaximalPower,
					highVoltageCableDescriptor);
			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	static public GenericItemUsingDamageDescriptor multiMeterElement,
			thermoMeterElement, allMeterElement;

	void registerMeter(int id) {
		int subId, completId;

		GenericItemUsingDamageDescriptor element;
		{
			subId = 0;
			completId = subId + (id << 6);
			element = new GenericItemUsingDamageDescriptor("MultiMeter");
			sharedItem.addElement(completId, element);
			multiMeterElement = element;
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			element = new GenericItemUsingDamageDescriptor("ThermoMeter");
			sharedItem.addElement(completId, element);
			thermoMeterElement = element;
		}
		{
			subId = 2;
			completId = subId + (id << 6);
			element = new GenericItemUsingDamageDescriptor("AllMeter");
			sharedItem.addElement(completId, element);
			allMeterElement = element;
		}
		{
			subId = 8;
			completId = subId + (id << 6);
			element = new WirelessSignalAnalyserItemDescriptor("Wireless analyser");
			sharedItem.addElement(completId, element);

		}

	}

	public static TreeResin treeResin;

	void registerTreeResinAndRubber(int id) {
		int subId, completId;
		String name;

		{
			TreeResin descriptor;
			subId = 0;
			completId = subId + (id << 6);
			name = "Tree resin";

			descriptor = new TreeResin(name);

			sharedItem.addElement(completId, descriptor);
			treeResin = descriptor;
		}
		{
			GenericItemUsingDamageDescriptor descriptor;
			subId = 1;
			completId = subId + (id << 6);
			name = "Rubber";

			descriptor = new GenericItemUsingDamageDescriptor(name);
			sharedItem.addElement(completId, descriptor);
		}
	}

	void registerTreeResinCollector(int id) {
		int subId, completId;
		String name;

		TreeResinCollectorDescriptor descriptor;
		{
			subId = 0;
			completId = subId + (id << 6);
			name = "tree resin collector";

			descriptor = new TreeResinCollectorDescriptor(name,obj.getObj("treeresincolector"));
			sixNodeItem.addDescriptor(completId, descriptor);
		}
	}
	void registerBatteryCharger(int id) {
		int subId, completId;
		String name;

		BatteryChargerDescriptor descriptor;
		{
			subId = 0;
			completId = subId + (id << 6);
			name = "Weak 50V battery charger";

			descriptor = new BatteryChargerDescriptor(
					name,obj.getObj("batterychargera"),
					lowVoltageCableDescriptor,//ElectricalCableDescriptor cable,
					LVU,200//double nominalVoltage,double nominalPower
					);
			sixNodeItem.addDescriptor(completId, descriptor);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			name = "50V battery charger";

			descriptor = new BatteryChargerDescriptor(
					name,obj.getObj("batterychargera"),
					lowVoltageCableDescriptor,//ElectricalCableDescriptor cable,
					LVU,400//double nominalVoltage,double nominalPower
					);
			sixNodeItem.addDescriptor(completId, descriptor);
		}
		{
			subId = 4;
			completId = subId + (id << 6);
			name = "200V battery charger";

			descriptor = new BatteryChargerDescriptor(
					name,obj.getObj("batterychargera"),
					meduimVoltageCableDescriptor,//ElectricalCableDescriptor cable,
					MVU,1000//double nominalVoltage,double nominalPower
					);
			sixNodeItem.addDescriptor(completId, descriptor);
		}
	}
	
	void registerElectricalDrill(int id) {
		int subId, completId;
		String name;

		ElectricalDrillDescriptor descriptor;
		{
			subId = 0;
			completId = subId + (id << 6);
			name = "Cheap electrical drill";

			descriptor = new ElectricalDrillDescriptor(name,// iconId, name,
					LVU, LVU * 1.25,// double nominalVoltage,double
									// maximalVoltage,
					10, 1000 // double operationTime,double operationEnergy
			);
			sharedItem.addElement(completId, descriptor);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			name = "Average electrical drill";

			descriptor = new ElectricalDrillDescriptor(name,// iconId, name,
					LVU, LVU * 1.25,// double nominalVoltage,double
									// maximalVoltage,
					5, 1500 // double operationTime,double operationEnergy
			);
			sharedItem.addElement(completId, descriptor);
		}
		{
			subId = 2;
			completId = subId + (id << 6);
			name = "Fast electrical drill";

			descriptor = new ElectricalDrillDescriptor(name,// iconId, name,
					LVU, LVU * 1.25,// double nominalVoltage,double
									// maximalVoltage,
					3, 2000 // double operationTime,double operationEnergy
			);
			sharedItem.addElement(completId, descriptor);
		}

	}

	void registerOreScanner(int id) {
		int subId, completId;
		String name;

		OreScanner descriptor;
		{
			subId = 0;
			completId = subId + (id << 6);
			name = "Basic ore scanner";

			descriptor = new OreScanner(name,// iconId, name,
					LVU, LVU * 1.25,// double nominalVoltage,double
									// maximalVoltage,
					2, 300// ,int operationRadius,double operationTime,double
							// operationEnergy

			);
			sharedItem.addElement(completId, descriptor);
		}
		{
			subId = 1;
			completId = subId + (id << 6);
			name = "Advanced ore scanner";

			descriptor = new OreScanner(name,// iconId, name,
					LVU, LVU * 1.25,// double nominalVoltage,double
									// maximalVoltage,
					4, 800// ,int operationRadius,double operationEnergy

			);
			sharedItem.addElement(completId, descriptor);
		}

	}

	public static MiningPipeDescriptor miningPipeDescriptor;

	void registerMiningPipe(int id) {
		int subId, completId;
		String name;

		MiningPipeDescriptor descriptor;
		{
			subId = 0;
			completId = subId + (id << 6);
			name = "Mining pipe";

			descriptor = new MiningPipeDescriptor(name// iconId, name
			);
			sharedItem.addElement(completId, descriptor);

			miningPipeDescriptor = descriptor;
		}

	}

	void registerSixNodeCache(int id) {
		int subId, completId;
		String name;

		SixNodeCacheItem descriptor;
		{
			subId = 0;
			completId = subId + (id << 6);
			name = "Stone cache";

			descriptor = new SixNodeCacheItem(name,
					this.obj.getObj("stonecache"), 1);
			sharedItem.addElement(completId, descriptor);

		}

	}

	void registerAutoMiner(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Auto miner";

			AutoMinerDescriptor desc = new AutoMinerDescriptor(name, LVU,
					LVU * 1.4,// double nominalVoltage,double maximalVoltage,
					1500, 0.01,// double nominalPower,double nominalDropFactor,
					1, 50// double pipeRemoveTime,double pipeRemoveEnergy
			);

			transparentNodeItem.addDescriptor(subId + (id << 6), desc);
		}
	}

	void registerRawCable(int id) {
		int subId, completId;
		String name;

		{
			GenericItemUsingDamageDescriptor descriptor;
			subId = 0;
			completId = subId + (id << 6);
			name = "Copper cable";

			descriptor = new GenericItemUsingDamageDescriptor(name);
			sharedItem.addElement(completId, descriptor);
		}
		{
			GenericItemUsingDamageDescriptor descriptor;
			subId = 1;
			completId = subId + (id << 6);
			name = "Iron cable";

			descriptor = new GenericItemUsingDamageDescriptor(name);
			sharedItem.addElement(completId, descriptor);
		}
		{
			GenericItemUsingDamageDescriptor descriptor;
			subId = 2;
			completId = subId + (id << 6);
			name = "Tungsten cable";

			descriptor = new GenericItemUsingDamageDescriptor(name);
			sharedItem.addElement(completId, descriptor);
		}
	}

	void registerBrush(int id) {
		
		int subId, completId;
		BrushDescriptor whiteDesc = null;
		String name = "";
		String[] subNames = { "black", "red", "green", "brown", "blue",
				"purple", "cyan", "silver", "gray", "pink", "lime", "yellow",
				"lightBlue", "magenta", "orange", "white" };
		for (int idx = 0; idx < 16; idx++) {
			subId = idx;
			name = subNames[idx] + " brush";
			BrushDescriptor desc = new BrushDescriptor(name);
			sharedItem.addElement(subId + (id << 6), desc);	
			whiteDesc = desc;
			
		}
		ItemStack emptyStack = findItemStack("white brush");
		whiteDesc.setLife(emptyStack, 0);
		
		for (int idx = 0; idx < 16; idx++) {

			GameRegistry.addShapelessRecipe(emptyStack.copy(),
					new ItemStack(Block.cloth,1,idx), 
					new ItemStack(Item.ingotIron));	
		}
		
		for (int idx = 0; idx < 16; idx++) {
			name = subNames[idx] + " brush";
			GameRegistry.addShapelessRecipe(findItemStack(name, 1),
					new ItemStack(Item.dyePowder,1,idx), 
					emptyStack.copy());	
		}
		
	}
	
	void registerElectricalTool(int id) {
		int subId, completId;
		ItemStack stack;
		String name;
		{
			subId = 0;
			name = "Small flashlight";
			
			ElectricalLampItem desc = new ElectricalLampItem(
					name,
					10,5,//int light,int range,
					6000,20,100//, energyStorage,discharg, charge
					);
			sharedItem.addElement(subId + (id << 6), desc);
		}	
		
		
		
		{
			subId = 8;
			name = "Portable electrical mining drill";
			
			ElectricalPickaxe desc = new ElectricalPickaxe(
					name,
					8,3,//float strengthOn,float strengthOff,
					40000,200,800//double energyStorage,double energyPerBlock,double chargePower
					);
			sharedItemStackOne.addElement(subId + (id << 6), desc);
		}	

		{
			subId = 12;
			name = "Portable electrical axe";
			
			ElectricalAxe desc = new ElectricalAxe(
					name,
					8,3,//float strengthOn,float strengthOff,
					40000,200,800//double energyStorage,double energyPerBlock,double chargePower
					);
			sharedItemStackOne.addElement(subId + (id << 6), desc);
		}	
		
				
		
	}
	
	
	void registerBatteryItem(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Portable battery";
			
			BatteryItem desc = new BatteryItem(
					name,
					20000,500,100,//double energyStorage,double chargePower,double dischargePower, 
					2//int priority
					);
			sharedItem.addElement(subId + (id << 6), desc);
		}	
		
		{
			subId = 1;
			name = "Portable battery pack";
			
			BatteryItem desc = new BatteryItem(
					name,
					60000,1500,300,//double energyStorage,double chargePower,double dischargePower, 
					2//int priority
					);
			sharedItem.addElement(subId + (id << 6), desc);
		}	
		
		{
			subId = 16;
			name = "Portable condensator";
			
			BatteryItem desc = new BatteryItem(
					name,
					5000,2000,500,//double energyStorage,double chargePower,double dischargePower, 
					1//int priority
					);
			sharedItem.addElement(subId + (id << 6), desc);
		}	
		{
			subId = 17;
			name = "Portable condensator pack";
			
			BatteryItem desc = new BatteryItem(
					name,
					15000,6000,1500,//double energyStorage,double chargePower,double dischargePower, 
					1//int priority
					);
			sharedItem.addElement(subId + (id << 6), desc);
		}	
		
	}

	void registerMiscItem(int id) {
		int subId, completId;
		String name;
		{
			subId = 0;
			name = "Cheap chip";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 1;
			name = "Advanced chip";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 2;
			name = "Machine block";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 3;
			name = "Electrical probe";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 4;
			name = "Thermal probe";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
/*
		{
			subId = 5;
			name = "Tin plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}*/
		{
			subId = 6;
			name = "Copper plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 7;
			name = "Iron plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 8;
			name = "Gold plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 9;
			name = "Plumb plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 10;
			name = "Silicon plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}

		{
			subId = 11;
			name = "Steel plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 12;
			name = "Coal plate";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}

		/*
		 * { subId = 12; name = "Stone plate";
		 * GenericItemUsingDamageDescriptorWithComment desc = new
		 * GenericItemUsingDamageDescriptorWithComment( name, new String[]{} );
		 * sharedItem.addElement(subId + (id << 6), desc); }
		 */

		{
			subId = 16;
			name = "Silicon dust";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 17;
			name = "Silicon ingot";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		/*
		 * { subId = 20; name = "Macerator sorter module";
		 * MaceratorSorterDescriptor desc = new MaceratorSorterDescriptor( name
		 * ); sharedItem.addElement(subId + (id << 6), desc); }
		 */

		{
			subId = 22;
			name = "Machine booster";
			MachineBoosterDescriptor desc = new MachineBoosterDescriptor(name);
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 23;
			name = "Advanced machine block";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 28;
			name = "Basic magnet";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 29;
			name = "Advanced magnet";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}
		{
			subId = 32;
			name = "Data logger print";
			DataLogsPrintDescriptor desc = new DataLogsPrintDescriptor(name);
			dataLogsPrintDescriptor = desc;
			sharedItem.addElement(subId + (id << 6), desc);
		}
		
		{
			subId = 33;
			name = "Signal antenna";
			GenericItemUsingDamageDescriptorWithComment desc = new GenericItemUsingDamageDescriptorWithComment(
					name, new String[] {});
			sharedItem.addElement(subId + (id << 6), desc);
		}		
		

	}

	public DataLogsPrintDescriptor dataLogsPrintDescriptor;

	void recipeGround() {
		GameRegistry.addRecipe(findItemStack("Ground cable"), 
				" C ", 
				" C ",
				"CCC", 
				Character.valueOf('C'), findItemStack("Copper cable"));
	}

	void recipeElectricalSource() {
		// Trololol
	}

	void recipeElectricalCable() {
		GameRegistry.addRecipe(signalCableDescriptor.newItemStack(1), 
				"R", 
				"C",
				Character.valueOf('C'), findItemStack("Iron cable"),
				Character.valueOf('R'), findItemStack("Rubber"));
		
		GameRegistry.addRecipe(lowVoltageCableDescriptor.newItemStack(1), 
				"R",
				"C", 
				Character.valueOf('C'), findItemStack("Copper cable"),
				Character.valueOf('R'), findItemStack("Rubber"));
		
		GameRegistry.addRecipe(meduimVoltageCableDescriptor.newItemStack(1),
				"R",
				"C", 
				Character.valueOf('C'),lowVoltageCableDescriptor.newItemStack(1),
				Character.valueOf('R'), findItemStack("Rubber"));
		
		GameRegistry.addRecipe(highVoltageCableDescriptor.newItemStack(1), 
				"R",
				"C", 
				Character.valueOf('C'),meduimVoltageCableDescriptor.newItemStack(1),
				Character.valueOf('R'), findItemStack("Rubber"));

		GameRegistry.addRecipe(signalCableDescriptor.newItemStack(6), 
				"RRR",
				"CCC", 
				"RRR", 
				Character.valueOf('C'), new ItemStack(Item.ingotIron), 
				Character.valueOf('R'),findItemStack("Rubber"));
		
		GameRegistry.addRecipe(lowVoltageCableDescriptor.newItemStack(6),
				"RRR",
				"CCC", 
				"RRR", 
				Character.valueOf('C'),findItemStack("Copper ingot"), 
				Character.valueOf('R'),findItemStack("Rubber"));

	}

	void recipeThermalCable() {

		GameRegistry.addRecipe(findItemStack("Copper thermal cable", 6),
				"SSS",
				"CCC",
				"SSS",
				Character.valueOf('S'), new ItemStack(Block.cobblestone), 
				Character.valueOf('C'),	findItemStack("Copper ingot"));
		
		GameRegistry.addRecipe(findItemStack("Copper thermal cable", 1),
				"S",
				"C",
				"S",
				Character.valueOf('S'), new ItemStack(Block.cobblestone),
				Character.valueOf('C'), findItemStack("Copper cable"));

		// for(int idx = 0;idx<16;idx++)
		GameRegistry.addRecipe(
				findItemStack("Isolated copper thermal cable", 3), 
				" W ",
				" S ",
				"CCC",
				Character.valueOf('W'),findItemStack("Rubber"),// new ItemStack(Block.cloth,1,idx),
				Character.valueOf('S'), new ItemStack(Block.sand),
				Character.valueOf('C'), findItemStack("Copper thermal cable"));

	}

	void recipeLampSocket() {
		GameRegistry.addRecipe(findItemStack("Lamp socket A", 3), 
				"G ", 
				"IG",
				"G ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass),
				Character.valueOf('I'), new ItemStack(Item.ingotIron));
		
		GameRegistry.addRecipe(findItemStack("Lamp socket B projector", 3),
				" I", 
				"IG", 
				" I",
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('I'), new ItemStack(Item.ingotIron));

		
		GameRegistry.addRecipe(findItemStack("Street light", 1),
				"G", 
				"I", 
				"I",
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('I'), new ItemStack(Item.ingotIron));

	}

	void recipeLampSupply(){
		GameRegistry.addRecipe(findItemStack("Lamp supply", 1),
				" I ",
				"ICI",
				" I ",
				Character.valueOf('C'), findItemStack("Copper ingot"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron));
		
	}
	void recipeDiode() {

		GameRegistry.addRecipe(findItemStack("Signal diode", 4), 
				" RB", 
				"IIR",
				" RB", 
				Character.valueOf('R'), new ItemStack(Item.redstone),
				Character.valueOf('I'), findItemStack("Iron cable"),
				Character.valueOf('B'), findItemStack("Rubber"));

		GameRegistry.addRecipe(findItemStack("10A diode", 3), 
				" RB", 
				"IIR",
				" RB", 
				Character.valueOf('R'), new ItemStack(Item.redstone),
				Character.valueOf('I'), new ItemStack(Item.ingotIron),
				Character.valueOf('B'), findItemStack("Rubber"));

		GameRegistry.addRecipe(findItemStack("25A diode"), 
				"D", 
				"D", 
				"D",
				Character.valueOf('D'), findItemStack("10A diode"));

	}

	void recipeSwitch() {
		GameRegistry.addRecipe(findItemStack("Signal voltage switch"),
				"  I",
				" I ",
				"CAC", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('A'), findItemStack("Rubber"), 
				Character.valueOf('I'), findItemStack("Copper cable"), 
				Character.valueOf('C'), findItemStack("Signal cable"));

		GameRegistry.addRecipe(findItemStack("Low voltage switch"),
				"  I",
				" I ",
				"CAC", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('A'),findItemStack("Rubber"), 
				Character.valueOf('I'),findItemStack("Copper cable"), 
				Character.valueOf('C'),findItemStack("Low voltage cable"));

		GameRegistry.addRecipe(findItemStack("Medium voltage switch"), 
				"  I",
				" I ", 
				"CAC", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('A'),	findItemStack("Rubber"), 
				Character.valueOf('I'),findItemStack("Copper cable"), 
				Character.valueOf('C'),findItemStack("Medium voltage cable"));

		GameRegistry.addRecipe(findItemStack("High voltage switch"), 
				" AI",
				"AIA", 
				"CAC", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('A'),findItemStack("Rubber"), 
				Character.valueOf('I'),findItemStack("Copper cable"), 
				Character.valueOf('C'),findItemStack("High voltage cable"));
	}
	
	
	void recipeElectricalRelay() {
		// TODO Auto-generated method stub
		GameRegistry.addRecipe(findItemStack("Low voltage relay"),
				"  I",
				" I ",
				"CRC", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('A'),	findItemStack("Rubber"),
				Character.valueOf('I'),	findItemStack("Copper cable"), 
				Character.valueOf('C'),	findItemStack("Low voltage cable"));

		GameRegistry.addRecipe(findItemStack("Medium voltage relay"),
				"  I",
				" I ",
				"CRC", 
				Character.valueOf('R'),new ItemStack(Item.redstone), 
				Character.valueOf('A'),findItemStack("Rubber"),
				Character.valueOf('I'),findItemStack("Copper cable"), 
				Character.valueOf('C'),findItemStack("Medium voltage cable"));

		GameRegistry.addRecipe(findItemStack("High voltage relay"), 
				"  I",
				" I ",
				"CRC", 
				Character.valueOf('R'),new ItemStack(Item.redstone), 
				Character.valueOf('A'),findItemStack("Rubber"), 
				Character.valueOf('I'),findItemStack("Copper cable"),
				Character.valueOf('C'),findItemStack("High voltage cable"));
	}
	
	void recipeWirelessSignal() {
		
		GameRegistry.addRecipe(findItemStack("Wireless signal transmitter"), 
				" S ",
				" R ", 
				"ICI", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('C'),findItemStack("Cheap chip"),
				Character.valueOf('S'),findItemStack("Signal antenna"));
		
		GameRegistry.addRecipe(findItemStack("Wireless signal repeater"), 
				"  S",
				"S R", 
				"ICI", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('C'),findItemStack("Cheap chip"),
				Character.valueOf('S'),findItemStack("Signal antenna"));
		
		GameRegistry.addRecipe(findItemStack("Wireless signal receivers"), 
				" S ", 
				"ICI", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('C'),findItemStack("Cheap chip"),
				Character.valueOf('S'),findItemStack("Signal antenna"));


	}

	void recipeTransformer() {
		for (int idx = 0; idx < 4; idx++) {
			GameRegistry.addRecipe(findItemStack("Transformer"), 
					"I I", 
					"WWW",
					Character.valueOf('W'),new ItemStack(Block.planks, 1, idx),
					Character.valueOf('I'), new ItemStack(Item.ingotIron));
		}
	}

	void recipeHeatFurnace() {
		GameRegistry.addRecipe(findItemStack("Stone heat furnace"), 
				"BBB",
				"BIB", 
				"BiB", 
				Character.valueOf('B'),new ItemStack(Block.stone), 
				Character.valueOf('i'),findItemStack("Copper thermal cable"), 
				Character.valueOf('I'),findItemStack("Combustion chamber"));
/*
		GameRegistry.addRecipe(findItemStack("Brick heat furnace"), "BIB",
				"BIB", "BBB", Character.valueOf('B'),
				new ItemStack(Block.brick), Character.valueOf('I'),
				findItemStack("Combustion chamber"));*/
	}

	void recipeTurbine() {
/*
		GameRegistry.addRecipe(findItemStack("Small 50V turbine"),
				" m ",
				"HMH",
				" E ",

				Character.valueOf('m'), findItemStack("Electrical motor"),
				Character.valueOf('M'), findItemStack("Machine block"),
				Character.valueOf('E'), findItemStack("Copper cable"),
				Character.valueOf('H'), findItemStack("Copper thermal cable"));
*/
		GameRegistry.addRecipe(findItemStack("50V turbine"), 
				" m ",
				"HMH",
				" E ", 
				Character.valueOf('M'), findItemStack("Machine block"),
				Character.valueOf('E'), findItemStack("Low voltage cable"),
				Character.valueOf('H'), findItemStack("Copper thermal cable"),
				Character.valueOf('m'), findItemStack("Electrical motor")

		);
		GameRegistry.addRecipe(findItemStack("200V turbine"), 
				"ImI", 
				"HMH",
				"IEI", 
				Character.valueOf('I'), findItemStack("Rubber"),
				Character.valueOf('M'),findItemStack("Advanced machine block"),
				Character.valueOf('E'), findItemStack("Medium voltage cable"),
				Character.valueOf('H'), findItemStack("Copper thermal cable"),
				Character.valueOf('m'),findItemStack("Advanced electrical motor"));

	}

	void recipeBattery() {

		GameRegistry.addRecipe(findItemStack("Cost oriented battery"),
				"C C",
				"PPP",
				"PPP",
				Character.valueOf('C'),findItemStack("Low voltage cable"),
				Character.valueOf('P'),findItemStack("Plumb ingot"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron));

		GameRegistry.addRecipe(findItemStack("Capacity oriented battery"),
				"PPP",
				"PBP",
				"PPP", 
				Character.valueOf('B'),findItemStack("Cost oriented battery"), 
				Character.valueOf('P'),findItemStack("Plumb ingot"));

		GameRegistry.addRecipe(findItemStack("Voltage oriented battery"),
				"PPP",
				"PBP",
				"PPP",
				Character.valueOf('B'),findItemStack("Cost oriented battery"),
				Character.valueOf('P'),new ItemStack(Item.ingotIron));

		GameRegistry.addRecipe(findItemStack("Current oriented battery"),
				"PPP",
				"PBP",
				"PPP",
				Character.valueOf('B'),findItemStack("Cost oriented battery"), 
				Character.valueOf('P'),findItemStack("Copper ingot"));

		GameRegistry.addRecipe(findItemStack("Life oriented battery"), 
				"PPP",
				"PBP",
				"PPP",
				Character.valueOf('B'),findItemStack("Cost oriented battery"), 
				Character.valueOf('P'),new ItemStack(Item.ingotGold));

		GameRegistry.addRecipe(findItemStack("Single usage battery"), 
				"C C",
				"ccc",
				"III",
				Character.valueOf('C'),findItemStack("Low voltage cable"), 
				Character.valueOf('c'),new ItemStack(Item.coal, 1, 0), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron));

		GameRegistry.addRecipe(findItemStack("Single usage battery"), 
				"C C",
				"ccc", 
				"III", 
				Character.valueOf('C'),findItemStack("Low voltage cable"), 
				Character.valueOf('c'),new ItemStack(Item.coal, 1, 1), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron));

	}

	void recipeElectricalFurnace() {

		GameRegistry.addRecipe(findItemStack("Electrical furnace"),
				"III",
				"IFI",
				"ICI", 
				Character.valueOf('C'),findItemStack("Low voltage cable"),
				Character.valueOf('F'),new ItemStack(Block.furnaceIdle),
				Character.valueOf('I'),new ItemStack(Item.ingotIron));
	}

	void recipeAutoMiner() {
		GameRegistry.addRecipe(findItemStack("Auto miner"),
				"CMC", 
				" B ",
				" P ", 
				Character.valueOf('C'), findItemStack("Cheap chip"),
				Character.valueOf('B'), findItemStack("Machine block"),
				Character.valueOf('M'), findItemStack("Electrical motor"),
				Character.valueOf('P'), findItemStack("Mining pipe"));
	}

	void recipeSolarPannel() {
		GameRegistry.addRecipe(findItemStack("Small solar pannel"), 
				"III",
				"CSC", 
				"III", 
				Character.valueOf('S'),findItemStack("Silicon plate"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('C'),findItemStack("Low voltage cable"));

		GameRegistry.addRecipe(findItemStack("Small rotating solar pannel"),
				"ISI",
				"I I", 
				Character.valueOf('S'),findItemStack("Small solar pannel"),
				Character.valueOf('M'),findItemStack("Electrical motor"),
				Character.valueOf('I'),new ItemStack(Item.ingotIron));

	}



	void recipeThermalDissipatorPassiveAndActive() {
		GameRegistry.addRecipe(
				findItemStack("Small passive thermal dissipator"),
				"I I",
				"III", 
				Character.valueOf('I'), findItemStack("Copper ingot"),
				Character.valueOf('C'), findItemStack("Copper thermal cable")
				);

		GameRegistry.addRecipe(
				findItemStack("Small active thermal dissipator"),
				"RMR",
				"I I",
				"III", 
				Character.valueOf('I'), findItemStack("Copper ingot"),
				Character.valueOf('M'), findItemStack("Electrical motor"),
				Character.valueOf('R'), findItemStack("Rubber"),
				Character.valueOf('C'), findItemStack("Copper thermal cable"));

		GameRegistry.addRecipe(
				findItemStack("Small active thermal dissipator"), 
				"RMR", 
				" D ",
				Character.valueOf('D'), findItemStack("Small passive thermal dissipator"),
				Character.valueOf('M'), findItemStack("Electrical motor"),
				Character.valueOf('R'), findItemStack("Rubber"));

	}

	void recipeGeneral() {
		FurnaceRecipes.smelting().addSmelting(treeResin.parentItem.itemID,
				treeResin.parentItemDamage, findItemStack("Rubber", 1), 0f);

	}

	void recipeHeatingCorp() {
		GameRegistry.addRecipe(findItemStack("Small 50V copper heating corp"),
				"CCC",
				"C C",
				"C C",
				Character.valueOf('C'),findItemStack("Copper cable"));
		
		GameRegistry.addRecipe(findItemStack("50V copper heating corp"), 
				"CCC",
				"C C", 
				"C C", 
				Character.valueOf('C'),findItemStack("Copper ingot"));
		
		GameRegistry.addRecipe(findItemStack("Small 200V copper heating corp"),
				"CC", 
				Character.valueOf('C'),findItemStack("50V copper heating corp"));
		
		GameRegistry.addRecipe(findItemStack("200V copper heating corp"),
				"CC",
				Character.valueOf('C'),findItemStack("Small 200V copper heating corp"));

		GameRegistry.addRecipe(findItemStack("Small 50V iron heating corp"),
				"CCC",
				"C C", 
				"C C", Character.valueOf('C'),findItemStack("Iron cable"));
		
		GameRegistry.addRecipe(findItemStack("50V iron heating corp"),
				"CCC",
				"C C",
				"C C", 
				Character.valueOf('C'), new ItemStack(Item.ingotIron));
		
		GameRegistry.addRecipe(findItemStack("Small 200V iron heating corp"),
				"CC",
				Character.valueOf('C'),findItemStack("50V iron heating corp"));
		
		GameRegistry.addRecipe(findItemStack("200V iron heating corp"),
				"CC",
				Character.valueOf('C'),findItemStack("Small 200V iron heating corp"));

		GameRegistry.addRecipe(findItemStack("Small 50V tungsten heating corp"),
				"CCC",
				"C C",
				"C C", 
				Character.valueOf('C'), findItemStack("Tungsten cable"));
		
		GameRegistry.addRecipe(findItemStack("50V tungsten heating corp"),
				"CCC",
				"C C", 
				"C C", 
				Character.valueOf('C'),findItemStack("Tungsten ingot"));
		
		GameRegistry.addRecipe(findItemStack("Small 200V tungsten heating corp"),
				"CC",
				Character.valueOf('C'),findItemStack("50V tungsten heating corp"));
		GameRegistry.addRecipe(findItemStack("200V tungsten heating corp"),
				"CC", 
				Character.valueOf('C'),findItemStack("Small 200V tungsten heating corp"));
	}

	void recipeThermalIsolator() {
	/*	GameRegistry
				.addRecipe(findItemStack("Sand thermal isolator"), "C C",
						" C ", "C C", Character.valueOf('C'), new ItemStack(
								Block.sand));
		GameRegistry.addRecipe(findItemStack("Stone thermal isolator"), "C C",
				" C ", "C C", Character.valueOf('C'),
				new ItemStack(Block.stone));
		GameRegistry
				.addRecipe(findItemStack("Brick thermal isolator"), "C C",
						" C ", "C C", Character.valueOf('C'), new ItemStack(
								Item.brick));
*/
	}

	void recipeRegulatorItem() {

		GameRegistry.addRecipe(findItemStack("On/OFF regulator 10%", 1),
				"R R",
				" R ",
				" I ", 
				Character.valueOf('R'), new ItemStack(Item.redstone),
				Character.valueOf('I'), new ItemStack(Item.ingotIron));

		GameRegistry.addRecipe(findItemStack("On/OFF regulator 10%", 1), 
				"RRR",
				" I ",
				Character.valueOf('R'), new ItemStack(Item.redstone),
				Character.valueOf('I'), new ItemStack(Item.ingotIron));

		GameRegistry.addRecipe(findItemStack("Analogic regulator", 1), 
				"R R",
				" C ", 
				" I ", 
				Character.valueOf('R'), new ItemStack(Item.redstone), 
				Character.valueOf('I'), new ItemStack(Item.ingotIron), 
				Character.valueOf('C'), findItemStack("Cheap chip"));
	}

	void recipeLampItem() {

		// Tungsten
		GameRegistry.addRecipe(
				findItemStack("Small 50V incandescent light bulb", 4), 
				" G ",
				"GFG", 
				" S ",
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Tungsten ingot"), 
				Character.valueOf('S'),findItemStack("Copper cable"));
		
		GameRegistry.addRecipe(findItemStack("50V incandescent light bulb", 4),
				" G ",
				"GFG",
				" S ",
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Tungsten ingot"), 
				Character.valueOf('S'),findItemStack("Low voltage cable"));
		
		GameRegistry.addRecipe(	findItemStack("200V incandescent light bulb", 4),
				" G ",
				"GFG",
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass),
				Character.valueOf('F'), findItemStack("Tungsten ingot"),
				Character.valueOf('S'), findItemStack("Medium voltage cable"));
		/*
		 * GameRegistry.addRecipe(
		 * findItemStack("400V incandescent light bulb",4)," G ", "GFG"," S ",
		 * Character.valueOf('G'), new ItemStack(Block.thinGlass),
		 * Character.valueOf('F'), findItemStack("Tungsten ingot"),
		 * Character.valueOf('S'), findItemStack("High voltage cable") );
		 */
		// CARBON
		GameRegistry.addRecipe(findItemStack("Small 50V carbon incandescent light bulb", 4),
				" G ", 
				"GFG", 
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass),
				Character.valueOf('F'),new ItemStack(Item.coal),
				Character.valueOf('S'),findItemStack("Copper cable"));
		
		GameRegistry.addRecipe(findItemStack("Small 50V carbon incandescent light bulb", 4),
				" G ",
				"GFG", 
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),new ItemStack(Item.coal, 1, 1), 
				Character.valueOf('S'),findItemStack("Copper cable"));
		
		GameRegistry.addRecipe(
				findItemStack("50V carbon incandescent light bulb", 4), 
				" G ",
				"GFG", 
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),new ItemStack(Item.coal), 
				Character.valueOf('S'),findItemStack("Low voltage cable"));
		
		GameRegistry.addRecipe(findItemStack("50V carbon incandescent light bulb", 4), 
				" G ",
				"GFG", 
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),new ItemStack(Item.coal, 1, 1),
				Character.valueOf('S'),findItemStack("Low voltage cable"));

		GameRegistry.addRecipe(
				findItemStack("Small 50V economic light bulb", 4), 
				" G ",
				"GFG", 
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Mercury"), 
				Character.valueOf('S'),findItemStack("Copper cable"));
		
		GameRegistry.addRecipe(findItemStack("50V economic light bulb", 4),
				" G ",
				"GFG", 
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Mercury"), 
				Character.valueOf('S'),findItemStack("Low voltage cable"));
		
		GameRegistry.addRecipe(findItemStack("200V economic light bulb", 4),
				" G ",
				"GFG",
				" S ", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Mercury"), 
				Character.valueOf('S'),findItemStack("Medium voltage cable"));
		
		
		GameRegistry.addRecipe(findItemStack("50V farming lamp", 2),
				"GGG",
				"FFF",
				"GSG",
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Tungsten ingot"), 
				Character.valueOf('S'),findItemStack("Low voltage cable"));
		
		GameRegistry.addRecipe(findItemStack("200V farming lamp", 2),
				"GGG",
				"FFF",
				"GSG",
				Character.valueOf('G'), new ItemStack(Block.thinGlass), 
				Character.valueOf('F'),findItemStack("Tungsten ingot"), 
				Character.valueOf('S'),findItemStack("Medium voltage cable"));
				
		
	}

	void recipeProtection() {

		GameRegistry.addRecipe(findItemStack("OverVoltage protection", 4),
				"SCD",
				Character.valueOf('S'),findItemStack("Electrical probe"), 
				Character.valueOf('C'),findItemStack("Cheap chip"),
				Character.valueOf('D'),new ItemStack(Item.redstone));
		
		GameRegistry.addRecipe(findItemStack("OverHeating protection", 4),
				"SCD", 
				Character.valueOf('S'), findItemStack("Thermal probe"),
				Character.valueOf('C'), findItemStack("Cheap chip"),
				Character.valueOf('D'), new ItemStack(Item.redstone));

	}

	void recipeCombustionChamber() {
		GameRegistry.addRecipe(findItemStack("Combustion chamber"),
				" L ",
				"L L", 
				" L ", 
				Character.valueOf('L'),new ItemStack(Block.stone));
	}

	void recipeFerromagneticCore() {
		GameRegistry.addRecipe(findItemStack("Cheap ferromagnetic core"),
				"LLL",
				"L  ", 
				"LLL", 
				Character.valueOf('L'), Item.ingotIron);
		
		GameRegistry.addRecipe(findItemStack("Average ferromagnetic core"),
				"LLL",
				"L  ", 
				"LLL", 
				Character.valueOf('L'),findItemStack("Ferrite ingot"));
		
		GameRegistry.addRecipe(findItemStack("Optimal ferromagnetic core"),
				"ll", 
				Character.valueOf('l'),findItemStack("Average ferromagnetic core"));
	}

	void recipeIngot() {
		// Done
	}

	void recipeDust() {
		GameRegistry.addShapelessRecipe(findItemStack("Steel dust"),
				findItemStack("Iron dust"), 
				findItemStack("Coal dust"));

	}

	void recipeElectricalMotor() {

		GameRegistry.addRecipe(findItemStack("Electrical motor"),
				" C ",
				"III",
				"C C",
				Character.valueOf('I'), new ItemStack(Item.ingotIron),
				Character.valueOf('C'), findItemStack("Low voltage cable"));

		GameRegistry.addRecipe(findItemStack("Advanced electrical motor"),
				"RCR", 
				"MIM", 
				"CRC",
				Character.valueOf('M'),findItemStack("Basic magnet"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('R'),new ItemStack(Item.redstone), 
				Character.valueOf('C'),findItemStack("Medium voltage cable"));

		// TODO
		/*
		 * GameRegistry.addRecipe( findItemStack("200V electrical motor"),
		 * "RFR", "GMG", "RFR", Character.valueOf('R'), new
		 * ItemStack(Item.redstone), Character.valueOf('G'), new
		 * ItemStack(Item.ingotGold), Character.valueOf('M'),
		 * findItemStack("50V electrical motor"), Character.valueOf('F'),
		 * findItemStack("Ferrite ingot") );
		 */
	}

	void recipeSolarTracker() {
		GameRegistry.addRecipe(findItemStack("Solar tracker",4), 
				"VVV", 
				"RQR",
				"III", 
				Character.valueOf('Q'),new ItemStack(Item.netherQuartz), 
				Character.valueOf('V'),new ItemStack(Block.thinGlass), 
				Character.valueOf('R'),new ItemStack(Item.redstone), 
				Character.valueOf('G'),new ItemStack(Item.ingotGold), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron));

	}

	void recipeDynamo() {

		/*
		 * GameRegistry.addRecipe( findItemStack("Small dynamo"), " C ", "PIP",
		 * " C ", Character.valueOf('P'), findItemStack("Iron plate"),
		 * Character.valueOf('I'), findItemStack("Copper ingot"),
		 * Character.valueOf('C'), findItemStack("Low voltage cable") );
		 * 
		 * 
		 * GameRegistry.addRecipe( findItemStack("Medium dynamo"), "CPC", "PIP",
		 * "CPC", Character.valueOf('P'), findItemStack("Iron plate"),
		 * Character.valueOf('I'), findItemStack("Copper ingot"),
		 * Character.valueOf('C'), findItemStack("Low voltage cable") );
		 */

		// todo
		/*
		 * GameRegistry.addRecipe( findItemStack("Big dynamo"), "RFR", "GMG",
		 * "RFR", Character.valueOf('R'), new ItemStack(Item.redstone),
		 * Character.valueOf('G'), new ItemStack(Item.ingotGold),
		 * Character.valueOf('M'), findItemStack("Big dynamo"),
		 * Character.valueOf('F'), findItemStack("Ferrite ingot") );
		 */
	}

	void recipeWindRotor() {

	}

	void recipeMeter() {
		GameRegistry.addRecipe(findItemStack("MultiMeter"), 
				"RGR", 
				"RER",
				"RCR", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass),
				Character.valueOf('C'), findItemStack("Electrical probe"),
				Character.valueOf('E'), new ItemStack(Item.redstone),
				Character.valueOf('R'), findItemStack("Rubber"));

		GameRegistry.addRecipe(findItemStack("ThermoMeter"), 
				"RGR",
				"RER",
				"RCR", 
				Character.valueOf('G'), new ItemStack(Block.thinGlass),
				Character.valueOf('C'), findItemStack("Thermal probe"),
				Character.valueOf('E'), new ItemStack(Item.redstone),
				Character.valueOf('R'), findItemStack("Rubber"));

		GameRegistry.addShapelessRecipe(findItemStack("AllMeter"),
				findItemStack("MultiMeter"), 
				findItemStack("ThermoMeter"));
		
		GameRegistry.addRecipe(findItemStack("Wireless analyser"),
				" S ",
				"RGR",
				"RER",
				Character.valueOf('G'), new ItemStack(Block.thinGlass),
				Character.valueOf('S'), findItemStack("Signal antenna"),
				Character.valueOf('E'), new ItemStack(Item.redstone),
				Character.valueOf('R'), findItemStack("Rubber"));
		
		
		

	}

	void recipeElectricalDrill() {
		GameRegistry.addRecipe(findItemStack("Cheap electrical drill"), 
				"CMC",
				" T ", 
				" P ", 
				Character.valueOf('T'),findItemStack("Mining pipe"), 
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('M'),	findItemStack("Electrical motor"), 
				Character.valueOf('P'),new ItemStack(Item.pickaxeIron));

		GameRegistry.addRecipe(findItemStack("Average electrical drill"),
				"RCR",
				" D ", 
				" d ", Character.valueOf('R'), Item.redstone,
				Character.valueOf('C'), findItemStack("Cheap chip"),
				Character.valueOf('D'),findItemStack("Cheap electrical drill"),
				Character.valueOf('d'), new ItemStack(Item.diamond));

		GameRegistry.addRecipe(findItemStack("Fast electrical drill"), "MCM",
				" T ", 
				" P ", 
				Character.valueOf('T'),findItemStack("Mining pipe"), 
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('M'),findItemStack("Advanced electrical motor"),
				Character.valueOf('P'), new ItemStack(Item.pickaxeDiamond));

	}

	void recipeOreScanner() {

		GameRegistry.addRecipe(findItemStack("Basic ore scanner"), 
				"IGI",
				"RCR", 
				"IGI", 
				Character.valueOf('C'),findItemStack("Cheap chip"),
				Character.valueOf('R'),new ItemStack(Item.redstone),
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('G'),new ItemStack(Item.ingotGold));

		GameRegistry.addRecipe(findItemStack("Advanced ore scanner"), 
				"GCG",
				"RSR", 
				"GRG", 
				Character.valueOf('S'),findItemStack("Basic ore scanner"), 
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('G'),new ItemStack(Item.glowstone), 
				Character.valueOf('R'),new ItemStack(Item.redstone));

	}

	void recipeMiningPipe() {
		GameRegistry.addRecipe(findItemStack("Mining pipe",4),
				"A", 
				"A", 
				"A",
				Character.valueOf('A'), findItemStack("Steel ingot"));
	}

	void recipeTreeResinAndRubber() {
		for (int idx = 0; idx < 4; idx++) {
			GameRegistry.addRecipe(findItemStack("tree resin collector"),
					"W W",
					" WW",
					Character.valueOf('W'), new ItemStack(Block.planks, 1, idx));
		}
		for (int idx = 0; idx < 4; idx++) {
			GameRegistry.addRecipe(findItemStack("tree resin collector"),
					"W W",
					"WW ", Character.valueOf('W'), new ItemStack(Block.planks, 1, idx));
		}

	}

	void recipeRawCable() {
		GameRegistry.addRecipe(findItemStack("Copper cable", 6), 
				"III",
				Character.valueOf('I'), findItemStack("Copper ingot"));
		
		GameRegistry.addRecipe(findItemStack("Iron cable", 6), 
				"III",
				Character.valueOf('I'), new ItemStack(Item.ingotIron));
		
		GameRegistry.addRecipe(findItemStack("Tungsten cable", 6), 
				"III",
				Character.valueOf('I'), findItemStack("Tungsten ingot"));

	}

	void recipeBatteryItem(){
		GameRegistry.addRecipe(findItemStack("Portable battery"),
				"I",
				"P",
				"P",
				Character.valueOf('P'), findItemStack("Plumb ingot"),
				Character.valueOf('I'), new ItemStack(Item.ingotIron));		
		GameRegistry.addShapelessRecipe(
				findItemStack("Portable battery pack"),
				findItemStack("Portable battery"),findItemStack("Portable battery"),findItemStack("Portable battery"));		
	}


	
	
	void recipeElectricalTool() {

		GameRegistry.addRecipe(findItemStack("Small flashlight"),
				"L",
				"B",
				"I",
				Character.valueOf('L'), findItemStack("50V incandescent light bulb"),
				Character.valueOf('B'), findItemStack("Portable battery"),
				Character.valueOf('R'), new ItemStack(Item.ingotIron));		
		
		GameRegistry.addRecipe(findItemStack("Portable electrical mining drill"),
				" T ",
				"IBI",
				" I ",
				Character.valueOf('T'), findItemStack("Average electrical drill"),
				Character.valueOf('B'), findItemStack("Portable battery"),
				Character.valueOf('I'), new ItemStack(Item.ingotIron));		
	
		GameRegistry.addRecipe(findItemStack("Portable electrical axe"),
				" T ",
				"IBI",
				" I ",
				Character.valueOf('T'), new ItemStack(Item.axeIron),
				Character.valueOf('B'), findItemStack("Portable battery"),
				Character.valueOf('R'), new ItemStack(Item.ingotIron));		
	
		
	}
	
	
	
	
	void recipePortableCondensator()
	{
		GameRegistry.addRecipe(findItemStack("Portable condensator"),
				"C",
				"c",
				Character.valueOf('C'), findItemStack("Coal plate"),
				Character.valueOf('c'), findItemStack("Copper plate")
				);		
	
		GameRegistry.addShapelessRecipe(findItemStack("Portable condensator pack"), 
				findItemStack("Portable condensator"),
				findItemStack("Portable condensator"),
				findItemStack("Portable condensator"));
	}
	

	void recipeMiscItem() {
		GameRegistry.addRecipe(findItemStack("Cheap chip"),
				" R ",
				"RSR",
				" R ",
				Character.valueOf('S'), findItemStack("Silicon ingot"),
				Character.valueOf('R'), new ItemStack(Item.redstone));
		GameRegistry.addRecipe(findItemStack("Advanced chip"), 
				"LRL",
				"RCR",
				"LRL",
				Character.valueOf('C'), findItemStack("Cheap chip"),
				Character.valueOf('L'), new ItemStack(Item.glowstone),
				Character.valueOf('R'), new ItemStack(Item.redstone));

		GameRegistry.addRecipe(findItemStack("Machine block"),
				"LLL",
				"L L",
				"LLL",
				Character.valueOf('L'), new ItemStack(Item.ingotIron));

		GameRegistry.addRecipe(findItemStack("Advanced machine block"),
				"LLL",
				"LCL",
				"LLL", 
				Character.valueOf('C'),findItemStack("Steel plate"), 
				Character.valueOf('L'),findItemStack("Steel ingot"));

		GameRegistry.addRecipe(findItemStack("Electrical probe"), 
				" R ",
				"RCR", 
				" R ", 
				Character.valueOf('C'),findItemStack("High voltage cable"),
				Character.valueOf('R'),	new ItemStack(Item.redstone));

		GameRegistry.addRecipe(findItemStack("Thermal probe"), 
				"RIR",
				"RGR",
				Character.valueOf('G'), new ItemStack(Item.ingotGold),
				Character.valueOf('I'), new ItemStack(Item.ingotIron),
				Character.valueOf('R'), new ItemStack(Item.redstone));
		GameRegistry.addRecipe(findItemStack("Thermal probe"), 
				"RGR", 
				"RIR",
				Character.valueOf('G'), new ItemStack(Item.ingotGold),
				Character.valueOf('I'), new ItemStack(Item.ingotIron),
				Character.valueOf('R'), new ItemStack(Item.redstone));
		
		GameRegistry.addRecipe(findItemStack("Signal antenna"),
				"c",
				"c",
				Character.valueOf('c'), findItemStack("Iron cable"));
				
		GameRegistry.addRecipe(findItemStack("Machine booster"), 
				"RpR", 
				"CcC",
				"RpR",
				Character.valueOf('R'), new ItemStack(Item.redstone),
				Character.valueOf('C'), findItemStack("Copper ingot"),
				Character.valueOf('c'), findItemStack("Cheap chip"),
				Character.valueOf('p'), findItemStack("Plumb ingot")
						);
		

	}

	void recipeMacerator() {
		float f = 3000;
		maceratorRecipes.addRecipe(new Recipe(findItemStack("Copper ore"),
				new ItemStack[] { findItemStack("Copper dust", 2) }, 1.0*f));		
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Block.oreIron),
				new ItemStack[] { findItemStack("Iron dust", 2) }, 1.0*f));	
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Block.oreGold),
						new ItemStack[] { findItemStack("Gold dust", 2) },3.0*f));
		maceratorRecipes.addRecipe(new Recipe(findItemStack("Plumb ore"),
				new ItemStack[] { findItemStack("Plumb dust", 2) }, 2.0*f));
		maceratorRecipes.addRecipe(new Recipe(findItemStack("Tungsten ore"),
				new ItemStack[] { findItemStack("Tungsten dust", 2) }, 2.0*f));
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Item.coal, 1, 0),
				new ItemStack[] { findItemStack("Coal dust", 2) }, 1.0*f));
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Item.coal, 1, 1),
				new ItemStack[] { findItemStack("Coal dust", 2) }, 1.0*f));
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Block.sand, 1),
				new ItemStack[] { findItemStack("Silicon dust", 1) }, 3.0*f));
		maceratorRecipes.addRecipe(new Recipe(findItemStack("Cinnabar ore"),
				new ItemStack[] { findItemStack("Cinnabar dust", 2) }, 2.0*f));
		
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Block.cobblestone),
				new ItemStack[] {new ItemStack(Block.gravel)}, 1.0*f));
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Block.gravel),
				new ItemStack[] {new ItemStack(Item.flint)}, 1.0*f));
		
		maceratorRecipes.addRecipe(new Recipe(new ItemStack(Block.dirt),
				new ItemStack[] {new ItemStack(Block.sand)}, 1.0*f));
	}
/*
	void recipeExtractor() {
		extractorRecipes.addRecipe(new Recipe(findItemStack("Tree resin"),
				new ItemStack[] { findItemStack("Rubber", 3) }, 1000.0));
		extractorRecipes.addRecipe(new Recipe(findItemStack("Cinnabar dust"),
				new ItemStack[] { findItemStack("Purified cinnabar dust", 1) },
				1000.0));
	}
*/
	void recipePlateMachine(){
		float f = 10000;
		plateMachineRecipes.addRecipe(new Recipe(
				findItemStack("Copper ingot", 4),
				findItemStack("Copper plate"), 1.0*f));
		
		compressorRecipes.addRecipe(new Recipe(findItemStack("Plumb ingot", 4),
				findItemStack("Plumb plate"), 1.0*f));
		
		plateMachineRecipes.addRecipe(new Recipe(
				findItemStack("Silicon ingot", 4),
				findItemStack("Silicon plate"), 1.0*f));
		
		plateMachineRecipes.addRecipe(new Recipe(findItemStack("Steel ingot", 4),
				findItemStack("Steel plate"), 1.0*f));
		
		plateMachineRecipes.addRecipe(new Recipe(new ItemStack(Item.ingotIron, 4,
				0), findItemStack("Iron plate"), 1.0*f));
		
		plateMachineRecipes.addRecipe(new Recipe(new ItemStack(Item.ingotGold, 4,
				0), findItemStack("Gold plate"),1.0*f));		
	}
	
	void recipeCompressor() {
		compressorRecipes.addRecipe(new Recipe(findItemStack("Coal plate", 4),
				new ItemStack[] { new ItemStack(Item.diamond) }, 80000.0));
		// extractorRecipes.addRecipe(new
		// Recipe(findItemStack("Cinnabar dust"),new
		// ItemStack[]{findItemStack("Purified cinnabar dust",1)}, 1000.0));

		compressorRecipes.addRecipe(new Recipe(findItemStack("Coal dust", 4),
				findItemStack("Coal plate"), 4000.0));


	}

	void recipemagnetiser() {
		magnetiserRecipes.addRecipe(new Recipe(new ItemStack(Item.ingotIron),
				new ItemStack[] { findItemStack("Basic magnet") }, 5000.0));
		magnetiserRecipes.addRecipe(new Recipe(findItemStack("Steel ingot", 1),
				new ItemStack[] { findItemStack("Advanced magnet") }, 15000.0));
	}

	void recipeFurnace() {
		ItemStack in;
		/*in = findItemStack("Tin ore");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Tin ingot"), 0);
		in = findItemStack("Tin dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Tin ingot"), 0);*/
		in = findItemStack("Copper ore");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Copper ingot"), 0);
		in = findItemStack("Copper dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Copper ingot"), 0);
		in = findItemStack("Plumb ore");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Plumb ingot"), 0);
		in = findItemStack("Plumb dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Plumb ingot"), 0);
		in = findItemStack("Tungsten ore");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Tungsten ingot"), 0);
		in = findItemStack("Tungsten dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Tungsten ingot"), 0);
		in = findItemStack("Steel ingot");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Ferrite ingot"), 0);
		in = findItemStack("Iron dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				new ItemStack(Item.ingotIron), 0);
		
		in = findItemStack("Gold dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				new ItemStack(Item.ingotGold), 0);
		 
		in = findItemStack("Tree resin");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Rubber",2), 0);

		in = findItemStack("Steel dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Steel ingot"), 0);

		in = findItemStack("Silicon dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Silicon ingot"), 0);

		//in = findItemStack("Purified cinnabar dust");
		in = findItemStack("Cinnabar dust");
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(),
				findItemStack("Mercury"), 0);

	}

	void recipeElectricalSensor() {
		GameRegistry.addRecipe(findItemStack("Voltage sensor", 1),
				"SC",
				Character.valueOf('S'),findItemStack("Electrical probe"), 
				Character.valueOf('C'),findItemStack("Signal cable")
		);

		GameRegistry.addRecipe(findItemStack("Electrical sensor", 1),
				"SCS",
				Character.valueOf('S'),findItemStack("Electrical probe"), 
				Character.valueOf('C'),findItemStack("Signal cable"));

	}

	void recipeThermalSensor() {

		GameRegistry.addRecipe(findItemStack("Thermal sensor", 1),
				"SC",
				Character.valueOf('S'),findItemStack("Thermal probe"), 
				Character.valueOf('C'),findItemStack("Signal cable")
		);

		GameRegistry.addRecipe(findItemStack("Temperature sensor", 1),
				"SCS",
				Character.valueOf('S'),findItemStack("Thermal probe"), 
				Character.valueOf('C'),findItemStack("Signal cable"));

	}

	void recipeMachine() {
		GameRegistry.addRecipe(findItemStack("50V macerator", 1),
				"IRI",
				"FMF",
				"IcI",
				Character.valueOf('M'), findItemStack("Machine block"),
				Character.valueOf('c'), findItemStack("Low voltage cable"),
				Character.valueOf('F'), new ItemStack(Item.flint),
				Character.valueOf('I'), new ItemStack(Item.ingotIron),
				Character.valueOf('R'), new ItemStack(Item.redstone));
		GameRegistry.addRecipe(findItemStack("200V macerator", 1), 
				"ICI",
				"DMD",
				"IcI", 
				Character.valueOf('M'),findItemStack("Advanced machine block"),
				Character.valueOf('C'), findItemStack("Advanced chip"),
				Character.valueOf('c'), findItemStack("Medium voltage cable"),
				Character.valueOf('D'), new ItemStack(Item.diamond),
				Character.valueOf('I'), findItemStack("Steel ingot"));
/*
		GameRegistry.addRecipe(findItemStack("50V extractor", 1), 
				"IRI",
				"FMF",
				"IcI",
				Character.valueOf('M'), findItemStack("Machine block"),
				Character.valueOf('c'), findItemStack("Low voltage cable"),
				Character.valueOf('F'), new ItemStack(Item.dyePowder, 1, 4),
				Character.valueOf('I'), new ItemStack(Item.ingotIron),
				Character.valueOf('R'), new ItemStack(Item.redstone));
		GameRegistry.addRecipe(findItemStack("200V extractor", 1),
				"ICI",
				"DMD",
				"IcI", Character.valueOf('M'),
				findItemStack("Advanced machine block"),
				Character.valueOf('C'), findItemStack("Advanced chip"),
				Character.valueOf('c'), findItemStack("Medium voltage cable"),
				Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 4),
				Character.valueOf('I'), findItemStack("Steel ingot"));
*/
		GameRegistry.addRecipe(findItemStack("50V compressor", 1),
				"IRI",
				"FMF",
				"IcI",
				Character.valueOf('M'),findItemStack("Machine block"), 
				Character.valueOf('c'),findItemStack("Low voltage cable"), 
				Character.valueOf('F'),findItemStack("Iron plate"),
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('R'),new ItemStack(Item.redstone));
		GameRegistry.addRecipe(findItemStack("200V compressor", 1),
				"ICI",
				"DMD",
				"IcI",
				Character.valueOf('M'), findItemStack("Advanced machine block"),
				Character.valueOf('C'), findItemStack("Advanced chip"),
				Character.valueOf('c'), findItemStack("Medium voltage cable"),
				Character.valueOf('D'), findItemStack("Steel plate"),
				Character.valueOf('I'), findItemStack("Steel ingot"));
		
		
		GameRegistry.addRecipe(findItemStack("50V plate machine", 1),
				"IRI",
				"IMI",
				"IcI",
				Character.valueOf('M'),findItemStack("Machine block"), 
				Character.valueOf('c'),findItemStack("Low voltage cable"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('R'),new ItemStack(Item.redstone));
		
		GameRegistry.addRecipe(findItemStack("200V plate machine", 1),
				"DCD",
				"DMD",
				"DcD",
				Character.valueOf('M'),findItemStack("Advanced machine block"),
				Character.valueOf('C'), findItemStack("Advanced chip"),
				Character.valueOf('c'), findItemStack("Medium voltage cable"),
				Character.valueOf('D'), findItemStack("Steel plate"),
				Character.valueOf('I'), findItemStack("Steel ingot"));

		GameRegistry.addRecipe(findItemStack("50V magnetizer", 1),
				"IRI",
				"cMc",
				"IcI",
				Character.valueOf('M'),findItemStack("Machine block"), 
				Character.valueOf('c'),findItemStack("Low voltage cable"),
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('R'),new ItemStack(Item.redstone));
		
		GameRegistry.addRecipe(findItemStack("200V magnetizer", 1),
				"ICI",
				"cMc",
				"IcI",
				Character.valueOf('M'),findItemStack("Advanced machine block"),
				Character.valueOf('C'), findItemStack("Advanced chip"),
				Character.valueOf('c'), findItemStack("Medium voltage cable"),
				Character.valueOf('I'), findItemStack("Steel ingot"));

	}

	
	private void recipeElectricalGate() {
		// TODO Auto-generated method stub
		GameRegistry.addShapelessRecipe(findItemStack("Electrical timeout"),
				new ItemStack(Item.redstoneRepeater),
				findItemStack("Cheap chip"));	
		
		GameRegistry.addRecipe(findItemStack("Electrical math", 1), 
				" c ",
				"cCc",
				" c ",
				Character.valueOf('c'),findItemStack("Signal cable"),
				Character.valueOf('C'),findItemStack("Cheap chip")
				);		
	}
	
	private void recipeElectricalRedstone() {
			
		GameRegistry.addRecipe(findItemStack("Redstone to voltage converter", 1), 
				"TCS",
				Character.valueOf('S'),findItemStack("Signal cable"),
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('T'),new ItemStack(Block.torchRedstoneActive)
				);		
		
		GameRegistry.addRecipe(findItemStack("Voltage to redstone converter", 1), 
				"CTR",
				Character.valueOf('R'),new ItemStack(Item.redstone),
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('T'),new ItemStack(Block.torchRedstoneActive)
				);			


	}

	private void recipeElectricalLightSensor() {		
		GameRegistry.addShapelessRecipe(findItemStack("Electrical daylight sensor"),
				new ItemStack(Block.daylightSensor),
				findItemStack("Redstone to voltage converter"));			
	}

	private void recipeElectricalVuMeter() {
		// TODO Auto-generated method stub
		for(int idx = 0;idx<4;idx++){
			GameRegistry.addRecipe(findItemStack("Analog vuMeter", 1), 
					"WWW",
					"RIr",
					"WSW",
					Character.valueOf('W'),new ItemStack(Block.planks,1,idx), 
					Character.valueOf('R'),new ItemStack(Item.redstone), 
					Character.valueOf('I'),new ItemStack(Item.ingotIron),
					Character.valueOf('r'),new ItemStack(Item.dyePowder,1,1),
					Character.valueOf('S'),findItemStack("Signal cable")
					);	
		}
		for(int idx = 0;idx<4;idx++){
			GameRegistry.addRecipe(findItemStack("Led vuMeter", 1), 
					" W ",
					"WTW",
					" S ",
					Character.valueOf('W'),new ItemStack(Block.planks,1,idx), 
					Character.valueOf('T'),new ItemStack(Block.torchRedstoneActive),
					Character.valueOf('S'),findItemStack("Signal cable")
					);			
		}
	}			


	private void recipeElectricalBreaker() {
		// TODO Auto-generated method stub
		GameRegistry.addRecipe(findItemStack("Electrical breaker", 1), 
				"crC",
				Character.valueOf('c'),findItemStack("OverVoltage protection"), 
				Character.valueOf('C'),findItemStack("OverHeating protection"), 
				Character.valueOf('r'),findItemStack("High voltage relay")
				);

	}
	
	private void recipeElectricalGateSource() {
		// TODO Auto-generated method stub
		GameRegistry.addRecipe(findItemStack("Signal source", 1), 
				"RsR",
				"rRr",
				" c ",
				Character.valueOf('M'),findItemStack("Machine block"), 
				Character.valueOf('c'),findItemStack("Signal cable"), 
				Character.valueOf('r'),findItemStack("Rubber"), 
				Character.valueOf('s'),new ItemStack(Item.stick), 
				Character.valueOf('R'),new ItemStack(Item.redstone));
		
		GameRegistry.addRecipe(findItemStack("Signal source B", 1), 
				" r ",
				"rRr",
				" c ",
				Character.valueOf('M'),findItemStack("Machine block"), 
				Character.valueOf('c'),findItemStack("Signal cable"), 
				Character.valueOf('r'),findItemStack("Rubber"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('R'),new ItemStack(Item.redstone));
	}
	
	private void recipeElectricalDataLogger() {
		GameRegistry.addRecipe(findItemStack("Data logger", 1), 
				"RRR",
				"RGR",
				"RCR",
				Character.valueOf('R'),findItemStack("Rubber"), 
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('G'),new ItemStack(Block.thinGlass)
		);	
	}

	private void recipeSixNodeCache()
	{
		GameRegistry.addRecipe(findItemStack("Stone cache", 4), 
				"s s",
				"   ",
				"s s",
				Character.valueOf('s'),new ItemStack(Block.stone)
		);			
	}
	
	private void recipeElectricalAlarm() {
		GameRegistry.addRecipe(findItemStack("Electrical alarm A", 1), 
				" T ",
				" M ",
				" c ",
				Character.valueOf('c'),findItemStack("Signal cable"), 
				Character.valueOf('T'),new ItemStack(Block.torchRedstoneActive), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('M'),new ItemStack(Block.music)
		);	
		GameRegistry.addRecipe(findItemStack("Electrical alarm B", 1), 
				"MTM",
				" c ",
				Character.valueOf('c'),findItemStack("Signal cable"), 
				Character.valueOf('T'),new ItemStack(Block.torchRedstoneActive), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron), 
				Character.valueOf('M'),new ItemStack(Block.music)
		);	

	}	
	
	private void recipeElectricalAntenna() {
		GameRegistry.addRecipe(findItemStack("Low power transmitter antenna", 1), 
				"R i",
				"CI ",
				"R i",
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('i'),new ItemStack(Item.ingotIron), 
				Character.valueOf('I'),findItemStack("Iron plate"), 
				Character.valueOf('R'),new ItemStack(Item.redstone)
		);
		GameRegistry.addRecipe(findItemStack("Low power receiver antenna", 1), 
				"i  ",
				" IC",
				"i ",
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('I'),findItemStack("Iron plate"), 
				Character.valueOf('i'),new ItemStack(Item.ingotIron),
				Character.valueOf('R'),new ItemStack(Item.redstone)
		);		
		GameRegistry.addRecipe(findItemStack("Medium power transmitter antenna", 1), 
				"c I",
				"CI ",
				"c I",
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('c'),findItemStack("Cheap chip"), 
				Character.valueOf('I'),findItemStack("Iron plate"), 
				Character.valueOf('R'),new ItemStack(Item.redstone)
		);
		GameRegistry.addRecipe(findItemStack("Medium power receiver antenna", 1), 
				"I  ",
				" IC",
				"I ",
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('I'),findItemStack("Iron plate"), 
				Character.valueOf('R'),new ItemStack(Item.redstone)
		);
		
		
		GameRegistry.addRecipe(findItemStack("High power transmitter antenna", 1), 
				"C I",
				"CI ",
				"C I",
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('c'),findItemStack("Cheap chip"), 
				Character.valueOf('I'),findItemStack("Iron plate"), 
				Character.valueOf('R'),new ItemStack(Item.redstone)
		);
		GameRegistry.addRecipe(findItemStack("High power receiver antenna", 1), 
				"I D",
				" IC",
				"I D",
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('I'),findItemStack("Iron plate"), 
				Character.valueOf('R'),new ItemStack(Item.redstone),
				Character.valueOf('R'),new ItemStack(Item.diamond)
		);	
		

		
		
	}
	
	private void recipeBatteryCharger() {
		GameRegistry.addRecipe(findItemStack("Weak 50V battery charger", 1), 
				"RIR",
				"III",
				"RcR",
				Character.valueOf('c'),findItemStack("Low voltage cable"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('R'),new ItemStack(Item.redstone));
		GameRegistry.addRecipe(findItemStack("50V battery charger", 1), 
				"RIR",
				"ICI",
				"RcR",
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('c'),findItemStack("Low voltage cable"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('R'),new ItemStack(Item.redstone));
		
		GameRegistry.addRecipe(findItemStack("200V battery charger", 1), 
				"RIR",
				"ICI",
				"RcR",
				Character.valueOf('C'),findItemStack("Advanced chip"), 
				Character.valueOf('c'),findItemStack("Medium voltage cable"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('R'),new ItemStack(Item.redstone));
		
	}	
	

	
	private void recipeEggIncubatore() {
		GameRegistry.addRecipe(findItemStack("50V Egg incubator", 1), 
				"IGG",
				"E G",
				"CII",
				Character.valueOf('C'),findItemStack("Cheap chip"), 
				Character.valueOf('E'),findItemStack("Small 50V tungsten heating corp"), 
				Character.valueOf('I'),new ItemStack(Item.ingotIron),
				Character.valueOf('G'),new ItemStack(Block.thinGlass));
		
	}
	
	void recipeArmor()
	{
		GameRegistry.addRecipe(new ItemStack(helmetCopper), 
				"CCC",
				"C C",
				Character.valueOf('C'),findItemStack("Copper ingot"));	
		
		GameRegistry.addRecipe(new ItemStack(plateCopper), 
				"C C",
				"CCC",
				"CCC",
				Character.valueOf('C'),findItemStack("Copper ingot"));	
		
		GameRegistry.addRecipe(new ItemStack(legsCopper), 
				"CCC",
				"C C",
				"C C",
				Character.valueOf('C'),findItemStack("Copper ingot"));	
		
		GameRegistry.addRecipe(new ItemStack(bootsCopper), 
				"C C",
				"C C",
				Character.valueOf('C'),findItemStack("Copper ingot"));	
	}

	
	
	void recipeTool()
	{
		GameRegistry.addRecipe(new ItemStack(shovelCopper), 
				" i ",
				" s ",
				" s ",
				Character.valueOf('i'),findItemStack("Copper ingot"),
				Character.valueOf('s'),new ItemStack(Item.stick)
				);	
		GameRegistry.addRecipe(new ItemStack(axeCopper), 
				"ii ",
				"is ",
				" s ",
				Character.valueOf('i'),findItemStack("Copper ingot"),
				Character.valueOf('s'),new ItemStack(Item.stick)
				);	
		GameRegistry.addRecipe(new ItemStack(hoeCopper), 
				"ii ",
				" s ",
				" s ",
				Character.valueOf('i'),findItemStack("Copper ingot"),
				Character.valueOf('s'),new ItemStack(Item.stick)
				);	
		GameRegistry.addRecipe(new ItemStack(pickaxeCopper), 
				"iii",
				" s ",
				" s ",
				Character.valueOf('i'),findItemStack("Copper ingot"),
				Character.valueOf('s'),new ItemStack(Item.stick)
				);	
		GameRegistry.addRecipe(new ItemStack(swordCopper), 
				" i ",
				" i ",
				" s ",
				Character.valueOf('i'),findItemStack("Copper ingot"),
				Character.valueOf('s'),new ItemStack(Item.stick)
				);	
			
	}
	
	public ItemStack findItemStack(String name, int stackSize) {
		return GameRegistry.findItemStack("Eln", name, stackSize);
	}

	public ItemStack findItemStack(String name) {
		return findItemStack(name, 1);
	}
}