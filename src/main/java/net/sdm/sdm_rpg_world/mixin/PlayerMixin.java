package net.sdm.sdm_rpg_world.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract  class PlayerMixin {

//    public Player originalInstance = (Player) (Object) this;
//
//    @Inject(method = "attack", at = @At("HEAD"),cancellable = true)
//    public void MyMixin(Entity p_36347_, CallbackInfo ci){
//        if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(originalInstance, p_36347_)) return;
//        if (p_36347_.isAttackable()) {
//            if (!p_36347_.skipAttackInteraction(originalInstance)) {
//                float f = (float)originalInstance.getAttributeValue(Attributes.ATTACK_DAMAGE);
//                float f1;
//                if (p_36347_ instanceof LivingEntity) {
//                    f1 = EnchantmentHelper.getDamageBonus(originalInstance.getMainHandItem(), ((LivingEntity)p_36347_).getMobType());
//                } else {
//                    f1 = EnchantmentHelper.getDamageBonus(originalInstance.getMainHandItem(), MobType.UNDEFINED);
//                }
//
//                float f2 = originalInstance.getAttackStrengthScale(0.5F);
//                f *= 0.2F + f2 * f2 * 0.8F;
//                f1 *= f2;
//                if (f > 0.0F || f1 > 0.0F) {
//                    boolean flag = f2 > 0.9F;
//                    boolean flag1 = false;
//                    float i = (float)originalInstance.getAttributeValue(Attributes.ATTACK_KNOCKBACK); // Forge: Initialize this value to the attack knockback attribute of the player, which is by default 0
//                    i += EnchantmentHelper.getKnockbackBonus(originalInstance);
//                    if (originalInstance.isSprinting() && flag) {
//                        originalInstance.level().playSound((Player)null, originalInstance.getX(), originalInstance.getY(), originalInstance.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, originalInstance.getSoundSource(), 1.0F, 1.0F);
//                        ++i;
//                        flag1 = true;
//                    }
//
//                    boolean flag2 = flag && originalInstance.fallDistance > 0.0F && !originalInstance.onGround() && !originalInstance.onClimbable() && !originalInstance.isInWater() && !originalInstance.hasEffect(MobEffects.BLINDNESS) && !originalInstance.isPassenger() && p_36347_ instanceof LivingEntity;
//                    flag2 = flag2 && !originalInstance.isSprinting();
////                    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(originalInstance, p_36347_, flag2, flag2 ? 1.5F : 1.0F);
////                    flag2 = hitResult != null;
////                    if (flag2) {
////                        f *= hitResult.getDamageModifier();
////                    }
//
//                    f += f1;
//                    boolean flag3 = false;
//                    double d0 = (double)(originalInstance.walkDist - originalInstance.walkDistO);
//                    if (flag && !flag2 && !flag1 && originalInstance.onGround() && d0 < (double)originalInstance.getSpeed()) {
//                        ItemStack itemstack = originalInstance.getItemInHand(InteractionHand.MAIN_HAND);
//                        flag3 = itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SWORD_SWEEP);
//                    }
//
//                    float f4 = 0.0F;
//                    boolean flag4 = false;
//                    int j = EnchantmentHelper.getFireAspect(originalInstance);
//                    if (p_36347_ instanceof LivingEntity) {
//                        f4 = ((LivingEntity)p_36347_).getHealth();
//                        if (j > 0 && !p_36347_.isOnFire()) {
//                            flag4 = true;
//                            p_36347_.setSecondsOnFire(1);
//                        }
//                    }
//
//                    Vec3 vec3 = p_36347_.getDeltaMovement();
//                    boolean flag5 = p_36347_.hurt(originalInstance.damageSources().playerAttack(originalInstance), f);
//                    if (flag5) {
//                        if (i > 0) {
//                            if (p_36347_ instanceof LivingEntity) {
//                                ((LivingEntity)p_36347_).knockback((double)((float)i * 0.5F), (double) Mth.sin(originalInstance.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(originalInstance.getYRot() * ((float)Math.PI / 180F))));
//                            } else {
//                                p_36347_.push((double)(-Mth.sin(originalInstance.getYRot() * ((float)Math.PI / 180F)) * (float)i * 0.5F), 0.1D, (double)(Mth.cos(originalInstance.getYRot() * ((float)Math.PI / 180F)) * (float)i * 0.5F));
//                            }
//
//                            originalInstance.setDeltaMovement(originalInstance.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
//                            originalInstance.setSprinting(false);
//                        }
//
//                        if (flag3) {
//                            float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(originalInstance) * f;
//
//                            for(LivingEntity livingentity : originalInstance.level().getEntitiesOfClass(LivingEntity.class, originalInstance.getItemInHand(InteractionHand.MAIN_HAND).getSweepHitBox(originalInstance, p_36347_))) {
//                                double entityReachSq = Mth.square(originalInstance.getEntityReach()); // Use entity reach instead of constant 9.0. Vanilla uses bottom center-to-center checks here, so don't update this to use canReach, since it uses closest-corner checks.
//                                if (livingentity != originalInstance && livingentity != p_36347_ && !originalInstance.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStand) || !((ArmorStand)livingentity).isMarker()) && originalInstance.distanceToSqr(livingentity) < entityReachSq) {
//                                    livingentity.knockback((double)0.4F, (double)Mth.sin(originalInstance.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(originalInstance.getYRot() * ((float)Math.PI / 180F))));
//                                    livingentity.hurt(originalInstance.damageSources().playerAttack(originalInstance), f3);
//                                }
//                            }
//
//                            originalInstance.level().playSound((Player)null, originalInstance.getX(), originalInstance.getY(), originalInstance.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, originalInstance.getSoundSource(), 1.0F, 1.0F);
//                            originalInstance.sweepAttack();
//                        }
//
//                        if (p_36347_ instanceof ServerPlayer && p_36347_.hurtMarked) {
//                            ((ServerPlayer)p_36347_).connection.send(new ClientboundSetEntityMotionPacket(p_36347_));
//                            p_36347_.hurtMarked = false;
//                            p_36347_.setDeltaMovement(vec3);
//                        }
//
//                        if (flag2) {
////                            originalInstance.level().playSound((Player)null, originalInstance.getX(), originalInstance.getY(), originalInstance.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, originalInstance.getSoundSource(), 1.0F, 1.0F);
////                            originalInstance.crit(p_36347_);
//                        }
//
//                        if (!flag2 && !flag3) {
//                            if (flag) {
//                                originalInstance.level().playSound((Player)null, originalInstance.getX(), originalInstance.getY(), originalInstance.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, originalInstance.getSoundSource(), 1.0F, 1.0F);
//                            } else {
//                                originalInstance.level().playSound((Player)null, originalInstance.getX(), originalInstance.getY(), originalInstance.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, originalInstance.getSoundSource(), 1.0F, 1.0F);
//                            }
//                        }
//
//                        if (f1 > 0.0F) {
//                            originalInstance.magicCrit(p_36347_);
//                        }
//
//                        originalInstance.setLastHurtMob(p_36347_);
//                        if (p_36347_ instanceof LivingEntity) {
//                            EnchantmentHelper.doPostHurtEffects((LivingEntity)p_36347_, originalInstance);
//                        }
//
//                        EnchantmentHelper.doPostDamageEffects(originalInstance, p_36347_);
//                        ItemStack itemstack1 = originalInstance.getMainHandItem();
//                        Entity entity = p_36347_;
//                        if (p_36347_ instanceof net.minecraftforge.entity.PartEntity) {
//                            entity = ((net.minecraftforge.entity.PartEntity<?>) p_36347_).getParent();
//                        }
//
//                        if (!originalInstance.level().isClientSide && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
//                            ItemStack copy = itemstack1.copy();
//                            itemstack1.hurtEnemy((LivingEntity)entity, originalInstance);
//                            if (itemstack1.isEmpty()) {
//                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(originalInstance, copy, InteractionHand.MAIN_HAND);
//                                originalInstance.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
//                            }
//                        }
//
//                        if (p_36347_ instanceof LivingEntity) {
//                            float f5 = f4 - ((LivingEntity)p_36347_).getHealth();
//                            originalInstance.awardStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
//                            if (j > 0) {
//                                p_36347_.setSecondsOnFire(j * 4);
//                            }
//
//                            if (originalInstance.level() instanceof ServerLevel && f5 > 2.0F) {
//                                int k = (int)((double)f5 * 0.5D);
//                                ((ServerLevel)originalInstance.level()).sendParticles(ParticleTypes.DAMAGE_INDICATOR, p_36347_.getX(), p_36347_.getY(0.5D), p_36347_.getZ(), k, 0.1D, 0.0D, 0.1D, 0.2D);
//                            }
//                        }
//
//                        originalInstance.causeFoodExhaustion(0.1F);
//                    } else {
//                        originalInstance.level().playSound((Player)null, originalInstance.getX(), originalInstance.getY(), originalInstance.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, originalInstance.getSoundSource(), 1.0F, 1.0F);
//                        if (flag4) {
//                            p_36347_.clearFire();
//                        }
//                    }
//                }
//                originalInstance.resetAttackStrengthTicker(); // FORGE: Moved from beginning of attack() so that getAttackStrengthScale() returns an accurate value during all attack events
//
//            }
//            ci.cancel();
//        }
//    }
}
