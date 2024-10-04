package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public interface IPrey {

    TagKey<EntityType<?>> getPredatorsTag();
}
