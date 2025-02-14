package samebutdifferent.ecologics.worldgen.feature.configurations;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record SurfaceMossPatchFeatureConfiguration(int tries, int xzSpread, int ySpread, boolean onFloor, boolean onCeiling, boolean onWalls, List<Block> canPlaceOn) implements FeatureConfiguration {
    public static final Codec<SurfaceMossPatchFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(SurfaceMossPatchFeatureConfiguration::tries), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7).forGetter(SurfaceMossPatchFeatureConfiguration::xzSpread), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(SurfaceMossPatchFeatureConfiguration::ySpread), Codec.BOOL.fieldOf("can_place_on_floor").orElse(false).forGetter(SurfaceMossPatchFeatureConfiguration::onFloor), Codec.BOOL.fieldOf("can_place_on_ceiling").orElse(false).forGetter(SurfaceMossPatchFeatureConfiguration::onCeiling), Codec.BOOL.fieldOf("can_place_on_wall").orElse(false).forGetter(SurfaceMossPatchFeatureConfiguration::onWalls), Registry.BLOCK.byNameCodec().listOf().fieldOf("can_be_placed_on").forGetter(SurfaceMossPatchFeatureConfiguration::canPlaceOn)).apply(instance, SurfaceMossPatchFeatureConfiguration::new));
    public static List<Direction> directions = Lists.newArrayList();

    public SurfaceMossPatchFeatureConfiguration(int tries, int xzSpread, int ySpread, boolean onFloor, boolean onCeiling, boolean onWalls, List<Block> canPlaceOn) {
        this.tries = tries;
        this.xzSpread = xzSpread;
        this.ySpread = ySpread;
        this.onFloor = onFloor;
        this.onCeiling = onCeiling;
        this.onWalls = onWalls;
        this.canPlaceOn = canPlaceOn;
        List<Direction> list = Lists.newArrayList();
        if (onCeiling) {
            list.add(Direction.UP);
        }

        if (onFloor) {
            list.add(Direction.DOWN);
        }

        if (onWalls) {
            Direction.Plane plane = Direction.Plane.HORIZONTAL;
            Objects.requireNonNull(list);
            plane.forEach(list::add);
        }

        directions = Collections.unmodifiableList(list);
    }

    public int tries() {
        return this.tries;
    }

    public int xzSpread() {
        return this.xzSpread;
    }

    public int ySpread() {
        return this.ySpread;
    }
}
