package net.telepathicgrunt.bumblezone.entities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.telepathicgrunt.bumblezone.Bumblezone;
import net.telepathicgrunt.bumblezone.world.dimension.BumblezoneDimension;

@Mod.EventBusSubscriber(modid = Bumblezone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeeAggressionBehavior
{
	private static final double AGGRESSION_DISTANCE = 64.0D;
	private static final EntityPredicate LINE_OF_SIGHT = (new EntityPredicate()).setDistance(AGGRESSION_DISTANCE).setLineOfSiteRequired();
	
	@Mod.EventBusSubscriber(modid = Bumblezone.MODID)
	private static class ForgeEvents
	{

		//bees attack player that picks up honey blocks
		@SubscribeEvent
		public static void HoneyPickupEvent(ItemPickupEvent event)
		{
			PlayerEntity playerEntity = event.getPlayer();
			World world = playerEntity.world;
			
			//Make sure we are on client I think. Vanilla does this check too
			//Also checks to make sure we are in dimension and that player isn't in creative or spectator
			if (!world.isRemote && playerEntity.dimension == BumblezoneDimension.bumblezone() && !playerEntity.isCreative() && !playerEntity.isSpectator())
			{
				//if player picks up a honey block, bees gets very mad...
				if(event.getStack().getItem() == Items.HONEY_BLOCK)
				{
					List<BeeEntity> beeList = world.getTargettableEntitiesWithinAABB(BeeEntity.class, LINE_OF_SIGHT, playerEntity, playerEntity.getBoundingBox().grow(AGGRESSION_DISTANCE));
					for(BeeEntity bee : beeList)
					{
						bee.setBeeAttacker(playerEntity);
						bee.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 1, false, false));
						bee.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 200, 1, false, false));
						bee.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 2, false, false));
					}
				}
			}
		}
		
		
		//bees attacks bear mobs that is in the dimension
		@SubscribeEvent
		public static void MobUpdateEvent(LivingUpdateEvent event)
		{
			Entity bearEntity = event.getEntity();
			
			//must be a bear animal
			if(bearEntity instanceof PolarBearEntity || bearEntity instanceof PandaEntity)
			{
				World world = bearEntity.world;
	
				//Make sure we are on client I think. Vanilla does this check too
				//Also checks to make sure we are in dimension and that player isn't in creative or spectator
				if (!world.isRemote && bearEntity.dimension == BumblezoneDimension.bumblezone())
				{
					List<BeeEntity> beeList = world.getTargettableEntitiesWithinAABB(BeeEntity.class, LINE_OF_SIGHT, (LivingEntity)bearEntity, bearEntity.getBoundingBox().grow(AGGRESSION_DISTANCE));
					for(BeeEntity bee : beeList)
					{
						bee.setBeeAttacker((LivingEntity)bearEntity);
					}
				}
			}
		}
		
	}
}