package blueduck.outerend.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.filler.SimpleFiller;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.RotateLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.ScaleLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.SafelistValidator;

import blueduck.outerend.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CragMoonFeature extends Feature<NoFeatureConfig> {

	public CragMoonFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {

		BlockState VIOLITE = BlockRegistry.VIOLITE.get().getDefaultState();
		List<BlockState> WHITELIST = Arrays.asList(VIOLITE, Blocks.AIR.getDefaultState(), Blocks.END_STONE.getDefaultState());

		pos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos).up(random.nextInt(20) + 40);

		double scale = MathHelper.lerp(random.nextDouble(), 0.2, 0.6);
		double yScale = MathHelper.lerp(random.nextDouble(), 0.6, 1.2);
		double uniformXrot = random.nextDouble() * 360;
		double uniformYrot = random.nextDouble() * 360;
		double uniformZrot = random.nextDouble() * 360;

		if (random.nextDouble() < 0.2) {
			scale *= 2.3;
		}

		double radius = 15;
		double innerRadius = radius - (radius / 5);

		Shape shape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
				/* Shape */
				.applyLayer(new AddLayer(
						/* Shape */
						Shapes.ellipsoid(radius, radius, radius)))
				/* Scale */
				.applyLayer(ScaleLayer.of(scale))
				/* Scale */
				.applyLayer(ScaleLayer.of(1, yScale, 1))
				/* Rotation */
				.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
				/* Movement */
				.applyLayer(new TranslateLayer(Position.of(pos)))
				/* Placement */
				.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
					validShape.fill(new SimpleFiller(reader, VIOLITE));
				});

		int extraBumps = random.nextInt(16) + 36;

		for (int i = 0; i < extraBumps; i++) {

			double Xrot = random.nextDouble() * 360;
			double Yrot = random.nextDouble() * 360;
			double Zrot = random.nextDouble() * 360;
			double movementX = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double movementY = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double movementZ = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double width = (radius / 5) + ((random.nextDouble() * innerRadius) / 5);
			double height = (radius / 5) + ((random.nextDouble() * innerRadius) / 5);
			double length = (radius - innerRadius) + radius;

			Shape bumpShape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
					/* Shape */
					.applyLayer(new AddLayer(
							/* Shape */
							Shapes.ellipsoid(width, height, length)
									/* Rotation */
									.applyLayer(new RotateLayer(Quaternion.of(Xrot, Yrot, Zrot, true)))))
					/* Scale */
					.applyLayer(ScaleLayer.of(scale))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(movementX, movementY, movementZ)))
					/* Scale */
					.applyLayer(ScaleLayer.of(1, yScale, 1))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(pos)))
					/* Placement */
					.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
						validShape.fill(new SimpleFiller(reader, VIOLITE));
					});
		}

		Shape innerShape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
				/* Shape */
				.applyLayer(new AddLayer(
						/* Shape */
						Shapes.ellipsoid(innerRadius, innerRadius, innerRadius)))
				/* Scale */
				.applyLayer(ScaleLayer.of(scale))
				/* Scale */
				.applyLayer(ScaleLayer.of(1, yScale, 1))
				/* Rotation */
				.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
				/* Movement */
				.applyLayer(new TranslateLayer(Position.of(pos)))
				/* Placement */
				.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
					validShape.fill(new SimpleFiller(reader, Blocks.AIR.getDefaultState()));
				});

		int extraInsideBumps = random.nextInt(16) + 32;

		for (int i = 0; i < extraInsideBumps; i++) {

			double ballMovementX = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double ballMovementY = (innerRadius) - (innerRadius / 5);
			double ballMovementZ = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double Xrot = random.nextDouble() * 360;
			double Yrot = random.nextDouble() * 360;
			double Zrot = random.nextDouble() * 360;
			double width = (radius / 5) + ((random.nextDouble() * innerRadius) / 7);
			double height = (radius / 5) + ((random.nextDouble() * innerRadius) / 7);
			double length = (radius / 9) + ((random.nextDouble() * innerRadius) / 12);

			Shape ballShape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
					/* Shape */
					.applyLayer(new AddLayer(
							/* Shape */
							Shapes.ellipsoid(width, height, length)))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(ballMovementX, ballMovementY, ballMovementZ)))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(Xrot, Yrot, Zrot, true)))
					/* Scale */
					.applyLayer(ScaleLayer.of(scale))
					/* Scale */
					.applyLayer(ScaleLayer.of(1, yScale, 1))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(pos)))
					/* Placement */
					.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
						validShape.fill(new SimpleFiller(reader, VIOLITE));
					});

		}

		int holesInside = random.nextInt(6);

		for (int i = 0; i < holesInside; i++) {

			double ballMovementX = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double ballMovementZ = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double Xrot = random.nextDouble() * 360;
			double Yrot = random.nextDouble() * 360;
			double Zrot = random.nextDouble() * 360;
			double width = (radius / 9) + ((random.nextDouble() * innerRadius) / 19);
			double height = (radius / 9) + ((random.nextDouble() * innerRadius) / 19);
			double length = ((radius - innerRadius) * (radius / 5)) + radius;

			Shape holeShape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
					/* Shape */
					.applyLayer(new AddLayer(
							/* Shape */
							Shapes.ellipsoid(width, height, length)))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(ballMovementX, 0, ballMovementZ)))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(Xrot, Yrot, Zrot, true)))
					/* Scale */
					.applyLayer(ScaleLayer.of(scale))
					/* Scale */
					.applyLayer(ScaleLayer.of(1, yScale, 1))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(pos)))
					/* Placement */
					.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
						validShape.fill(new SimpleFiller(reader, Blocks.AIR.getDefaultState()));
					});

		}

//		int insideCrystals = random.nextInt(16) + 4;
//
//		for (int i = 0; i < insideCrystals; i++) {
//			BlockState CRYSTAL_TYPE = BlockTags.getCollection().getTagByID(new ResourceLocation("outer_end:crystal_blocks")).getRandomElement(random).getDefaultState();
//
//			double shardLength = (random.nextDouble() * 7) + 3;
//			double shardRadius = (random.nextDouble() * 0.3) + 2;
//			double shardArc = random.nextDouble() * 30;
//
//			double Xrot = random.nextDouble() * 25;
//			double Zrot = 180 - (random.nextDouble() * 10);
//			double Yrot = (random.nextDouble() * 360) * (i + 1);
//
//			double totalXrot = random.nextDouble() * 360;
//			double totalYrot = random.nextDouble() * 360;
//			double totalZrot = random.nextDouble() * 360;
//
//			double shardMovementX = (random.nextDouble() * (radius / 3)) - (radius / 6);
//			double shardMovementY = (innerRadius) - (innerRadius / 5);
//			double shardMovementZ = (random.nextDouble() * (radius / 3)) - (radius / 6);
//
//			Shape crystalShape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
//					/* Shape */
//					.applyLayer(new AddLayer(
//							/* Shape */
//							Shapes.bentCone(shardRadius, shardRadius, shardLength, shardArc)
//									/* Rotation */
//									.applyLayer(new RotateLayer(Quaternion.of(Xrot, Yrot, Zrot, true)))))
//					/* Movement */
//					.applyLayer(new TranslateLayer(Position.of(shardMovementX, shardMovementY, shardMovementZ)))
//					/* Rotation */
//					.applyLayer(new RotateLayer(Quaternion.of(totalXrot, totalYrot, totalZrot, true)))
//					/* Scale */
//					.applyLayer(ScaleLayer.of(scale))
//					/* Scale */
//					.applyLayer(ScaleLayer.of(1, yScale, 1))
//					/* Rotation */
//					.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
//					/* Movement */
//					.applyLayer(new TranslateLayer(Position.of(pos)))
//					/* Placement */
//					.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
//						validShape.fill(new SimpleFiller(reader, CRYSTAL_TYPE));
//					});
//		}

		int outsideCrystals = random.nextInt(16) + 4;

		for (int i = 0; i < outsideCrystals; i++) {
			BlockState CRYSTAL_TYPE = BlockTags.getCollection().getTagByID(new ResourceLocation("outer_end:crystal_blocks")).getRandomElement(random).getDefaultState();

			double shardLength = (random.nextDouble() * (radius / 2)) + (radius / 5);
			double shardRadius = (random.nextDouble() * 0.3) + 2;
			double shardArc = random.nextDouble() * 30;

			double Xrot = random.nextDouble() * 25;
			double Zrot = random.nextDouble() * 10;
			double Yrot = (random.nextDouble() * 360) * (i + 1);

			double totalXrot = random.nextDouble() * 360;
			double totalYrot = random.nextDouble() * 360;
			double totalZrot = random.nextDouble() * 360;

			double shardMovementX = (random.nextDouble() * (radius / 3)) - (radius / 6);
			double shardMovementY = radius;
			double shardMovementZ = (random.nextDouble() * (radius / 3)) - (radius / 6);

			Shape crystalShape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0))
					/* Shape */
					.applyLayer(new AddLayer(
							/* Shape */
							Shapes.bentCone(shardRadius, shardRadius, shardLength, shardArc)
									/* Rotation */
									.applyLayer(new RotateLayer(Quaternion.of(Xrot, Yrot, Zrot, true)))))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(shardMovementX, shardMovementY, shardMovementZ)))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(totalXrot, totalYrot, totalZrot, true)))
					/* Scale */
					.applyLayer(ScaleLayer.of(scale))
					/* Scale */
					.applyLayer(ScaleLayer.of(1, yScale, 1))
					/* Rotation */
					.applyLayer(new RotateLayer(Quaternion.of(uniformXrot, uniformYrot, uniformZrot, true)))
					/* Movement */
					.applyLayer(new TranslateLayer(Position.of(pos)))
					/* Placement */
					.validate(new SafelistValidator(reader, WHITELIST), (validShape) -> {
						validShape.fill(new SimpleFiller(reader, CRYSTAL_TYPE));
					});
		}

		return true;
	}

	private boolean isTouching(ISeedReader reader, BlockPos pos, BlockState state, int scale) {
		return reader.getBlockState(pos.up(scale)) == state || reader.getBlockState(pos.down(scale)) == state || reader.getBlockState(pos.north(scale)) == state || reader.getBlockState(pos.east(scale)) == state || reader.getBlockState(pos.south(scale)) == state || reader.getBlockState(pos.west(scale)) == state;
	}

	private boolean isInRange(ISeedReader reader, BlockPos pos, BlockState state, int range) {
		boolean yes = false;
		for (int i = 0; i < range; i++) {
			if (yes = false) {
				yes = isTouching(reader, pos, state, i + 1);
			} else {
				return yes;
			}
		}
		return yes;
	}

}
