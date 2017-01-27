package com.iesika.ComplexControl;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import li.cil.oc.integration.ManagedTileEntityEnvironment;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class DriverIC2Crop extends DriverSidedTileEntity {

	@Override
	public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing arg2) {
		return new Environment((ICropTile)world.getTileEntity(pos));
	}

	@Override
	public Class<?> getTileEntityClass() {
		return ICropTile.class;
	}

	public static final class Environment extends ManagedTileEntityEnvironment<ICropTile> implements NamedBlock {

		public Environment(ICropTile tileEntity) {
			super(tileEntity, "crop");
		}

		@Override
		public String preferredName() {
			return "crop";
		}

		@Override
		public int priority() {
			return 0;
		}

		@Callback(doc = "function():number -- Set the crop ScanLevel.")
		public Object[] setScanLevel(Context context, Arguments args) {
			final int scanlevel = args.checkInteger(0);
			this.tileEntity.setScanLevel(scanlevel);
			return new Object[] { scanlevel };
		}

		@Callback(doc = "function():number -- Get the crop ScanLevel.")
		public Object[] getScanLevel(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).getScanLevel() };
		}

		@Callback(doc = "function():number -- Get the crop GrowthPoint.")
		public Object[] getGrowthPoints(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).getGrowthPoints() };
		}

		@Callback(doc = "function():number -- Get the crop Gain.")
		public Object[] getGain(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).getStatGain() };
		}

		@Callback(doc = "function():number -- Get the crop Growth.")
		public Object[] getGrowth(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).getStatGrowth() };
		}

		@Callback(doc = "function():number -- Get the crop Resistance.")
		public Object[] getResistance(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).getStatResistance() };
		}

		@Callback(doc = "function():number -- Get the crop CurrentSize.")
		public Object[] getCurrentSize(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).getCurrentSize() };
		}

		@Callback(doc = "function():number -- Get the crop MaxSize.")
		public Object[] getMaxSize(Context context, Arguments args) {
			CropCard cc = ((ICropTile) this.tileEntity).getCrop();
			return new Object[] { cc == null? null : cc.getMaxSize()};
		}

		@Callback(doc = "function():string -- Get the crop Id.")
		public Object[] getId(Context context, Arguments args) {
			CropCard cc = ((ICropTile) this.tileEntity).getCrop();
			return new Object[] { cc == null? null : cc.getId() };
		}

		@Callback(doc = "function():string -- Get whether crop is crossing base.")
		public Object[] isCrossingBase(Context context, Arguments args) {
			return new Object[] { ((ICropTile) this.tileEntity).isCrossingBase() };
		}


	}

}
