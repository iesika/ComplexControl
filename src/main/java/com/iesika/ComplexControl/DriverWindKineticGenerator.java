package com.iesika.ComplexControl;

import ic2.core.WorldData;
import ic2.core.block.kineticgenerator.tileentity.TileEntityWindKineticGenerator;
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

public class DriverWindKineticGenerator extends DriverSidedTileEntity {

	@Override
	public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing arg2) {
		return new Environment((TileEntityWindKineticGenerator) world.getTileEntity(pos));
	}

	@Override
	public Class<?> getTileEntityClass() {
		return TileEntityWindKineticGenerator.class;
	}

	public static final class Environment extends ManagedTileEntityEnvironment<TileEntityWindKineticGenerator> implements NamedBlock {

		public Environment(TileEntityWindKineticGenerator tileEntity) {
			super(tileEntity, "windturbine");
		}

		@Override
		public String preferredName() {
			return "windturbine";
		}

		@Override
		public int priority() {
			return 0;
		}

		@Callback(doc = "function():number -- get wind strength.")
		public Object[] getWindStrength(Context context, Arguments args) {
			return new Object[] { WorldData.get(this.tileEntity.getWorld()).windSim.getWindAt((double)this.tileEntity.getPos().getY())};//this.tileEntity.getWindStrength()};
		}

		@Callback(doc = "function():number -- get min wind strength.")
		public Object[] getMinWindStrength(Context context, Arguments args) {
			return new Object[] { this.tileEntity.getMinWindStrength()};
		}

		@Callback(doc = "function():number -- get max wind strength.")
		public Object[] getMaxWindStrength(Context context, Arguments args) {
			return new Object[] { this.tileEntity.getMaxWindStrength()};
		}
	}
}