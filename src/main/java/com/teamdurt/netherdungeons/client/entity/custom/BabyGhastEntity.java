package com.teamdurt.netherdungeons.client.entity.custom;

import com.teamdurt.netherdungeons.effect.NDEffects;
import com.teamdurt.netherdungeons.init.NDEntityTypes;
import com.teamdurt.netherdungeons.init.NDItems;
import com.teamdurt.netherdungeons.sound.NDSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BabyGhastEntity extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> HOVERING =
            SynchedEntityData.defineId(BabyGhastEntity.class, EntityDataSerializers.BOOLEAN);

    public BabyGhastEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 0;
        this.setNoAi(false);
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOVERING, false);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.FLYING_SPEED, 0.4f).build();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setHovering(tag.getBoolean("isHovering"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isHovering", this.isHovering());
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BabyGhastHoverGoal(this));
        this.goalSelector.addGoal(2, new BabyGhastFollowOwnerGoal(this, 1.2, 10f, 5f));
        this.goalSelector.addGoal(3, new BabyGhastGiveVampirismEffectGoal(this));
        this.goalSelector.addGoal(4, new TranslateBabyGhastIntoGhastGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1, 20) {
            @Override
            protected Vec3 getPosition() {
                RandomSource random = BabyGhastEntity.this.getRandom();
                double dir_x = BabyGhastEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
                double dir_y = BabyGhastEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
                double dir_z = BabyGhastEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
                return new Vec3(dir_x, dir_y, dir_z);
            }
        });
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 30.0f, 1));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public void setHovering(boolean hovering) {
        this.entityData.set(HOVERING, hovering);
    }

    public boolean isHovering() {
        return this.entityData.get(HOVERING);
    }

    @Override
    public boolean canBeLeashed(Player p_21813_) {
        return true;
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public boolean canMate(Animal p_27569_) {
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() + 1;
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        return false;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(NDItems.ARTICHOKE_FRUIT.get());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(NDItems.ARTICHOKE_FRUIT.get()) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.heal((float) itemstack.getFoodProperties(this).getNutrition());
                    this.gameEvent(GameEvent.EAT, this);
                    return InteractionResult.SUCCESS;
                }
                if (!(item instanceof DyeItem)) {
                    InteractionResult interactionresult = super.mobInteract(player, hand);
                    if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                        this.setHovering(!this.isHovering());
                        this.navigation.stop();
                        this.setTarget((LivingEntity) null);
                        return InteractionResult.SUCCESS;
                    }
                    return interactionresult;
                }
            } else if (itemstack.is(NDItems.ARTICHOKE_FRUIT.get())) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if (this.random.nextInt(101) <= 24 && !ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);
                    this.setHovering(true);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            }
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float p_27568_) {
        this.setHovering(false);
        return super.hurt(source, p_27568_);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return NDSounds.BABY_GHAST_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return NDSounds.BABY_GHAST_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return NDSounds.BABY_GHAST_DEATH.get();
    }

    private static class BabyGhastHoverGoal extends Goal {
        private final BabyGhastEntity babyGhast;

        public BabyGhastHoverGoal(BabyGhastEntity babyGhast) {
            this.babyGhast = babyGhast;
        }

        @Override
        public boolean canUse() {
            return this.babyGhast.isHovering();
        }

        @Override
        public boolean canContinueToUse() {
            return this.babyGhast.isHovering();
        }

        @Override
        public void tick() {
            super.tick();
            this.babyGhast.getNavigation().stop();
        }
    }

    public static void init() {
        SpawnPlacements.register(NDEntityTypes.BABY_GHAST.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules);
        DungeonHooks.addDungeonMob(NDEntityTypes.BABY_GHAST.get(), 180);
    }

    public static class BabyGhastFollowOwnerGoal extends Goal {
        public static final int TELEPORT_WHEN_DISTANCE_IS = 50;
        private final BabyGhastEntity babyGhast;
        private LivingEntity owner;
        private final LevelReader level;
        private final double speedModifier;
        private final PathNavigation navigation;
        private int timeToRecalcPath;
        private final float stopDistance;
        private final float startDistance;
        private float oldWaterCost;
        private final boolean canFly;

        public BabyGhastFollowOwnerGoal(BabyGhastEntity babyGhast, double speedModifier, float startDistance, float stopDistance) {
            this.babyGhast = babyGhast;
            this.level = babyGhast.level;
            this.speedModifier = speedModifier;
            this.navigation = babyGhast.getNavigation();
            this.startDistance = startDistance;
            this.stopDistance = stopDistance;
            this.canFly = true;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            if (!(babyGhast.getNavigation() instanceof GroundPathNavigation) && !(babyGhast.getNavigation() instanceof FlyingPathNavigation)) {
                throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
            }
        }

        public boolean canUse() {
            LivingEntity $$0 = this.babyGhast.getOwner();
            if ($$0 == null) {
                return false;
            } else if ($$0.isSpectator()) {
                return false;
            } else if (this.babyGhast.isHovering()) {
                return false;
            } else if (this.babyGhast.distanceToSqr($$0) < (double) (this.startDistance * this.startDistance)) {
                return false;
            } else {
                this.owner = $$0;
                return true;
            }
        }

        public boolean canContinueToUse() {
            if (this.navigation.isDone()) {
                return false;
            } else if (this.babyGhast.isHovering()) {
                return false;
            } else {
                return !(this.babyGhast.distanceToSqr(this.owner) <= (double) (this.stopDistance * this.stopDistance));
            }
        }

        public void start() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.babyGhast.getPathfindingMalus(BlockPathTypes.WATER);
            this.babyGhast.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        }

        public void stop() {
            this.owner = null;
            this.navigation.stop();
            this.babyGhast.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
        }

        public void tick() {
            this.babyGhast.getLookControl().setLookAt(this.owner, 10.0F, (float) this.babyGhast.getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                if (!this.babyGhast.isLeashed() && !this.babyGhast.isPassenger()) {
                    if (this.babyGhast.distanceToSqr(this.owner) >= TELEPORT_WHEN_DISTANCE_IS * TELEPORT_WHEN_DISTANCE_IS ) {
                        this.teleportToOwner();
                    } else {
                        this.navigation.moveTo(this.owner, this.speedModifier);
                    }

                }
            }
        }

        private void teleportToOwner() {
            BlockPos $$0 = this.owner.blockPosition();

            for (int $$1 = 0; $$1 < 10; ++$$1) {
                int $$2 = this.randomIntInclusive(-3, 3);
                int $$3 = this.randomIntInclusive(-1, 3);
                int $$4 = this.randomIntInclusive(-3, 3);
                boolean $$5 = this.maybeTeleportTo($$0.getX() + $$2, $$0.getY() + $$3, $$0.getZ() + $$4);
                if ($$5) {
                    return;
                }
            }

        }

        private boolean maybeTeleportTo(int p_25304_, int p_25305_, int p_25306_) {
            if (Math.abs((double) p_25304_ - this.owner.getX()) < 2.0 && Math.abs((double) p_25306_ - this.owner.getZ()) < 2.0) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(p_25304_, p_25305_, p_25306_))) {
                return false;
            } else {
                this.babyGhast.moveTo((double) p_25304_ + 0.5, (double) p_25305_, (double) p_25306_ + 0.5, this.babyGhast.getYRot(), this.babyGhast.getXRot());
                this.navigation.stop();
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            if (!this.level.getBlockState(pos).is(Blocks.AIR)) {
                return false;
            } else {
                BlockState $$2 = this.level.getBlockState(pos.below());
                if (!this.canFly && $$2.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos $$3 = pos.subtract(this.babyGhast.blockPosition());
                    return this.level.noCollision(this.babyGhast, this.babyGhast.getBoundingBox().move($$3));
                }
            }
        }

        private int randomIntInclusive(int p_25301_, int p_25302_) {
            return this.babyGhast.getRandom().nextInt(p_25302_ - p_25301_ + 1) + p_25301_;
        }
    }

    private static class BabyGhastGiveVampirismEffectGoal extends Goal {
        private final BabyGhastEntity babyGhast;
        private LivingEntity owner;
        private int effectLevel;

        public BabyGhastGiveVampirismEffectGoal(BabyGhastEntity babyGhast) {
            this.babyGhast = babyGhast;
        }

        @Override
        public boolean canUse() {
            if (this.babyGhast.getOwner() == null || this.babyGhast.isHovering() || this.babyGhast.distanceToSqr(this.babyGhast.getOwner()) > 100) return false;
            this.owner = this.babyGhast.getOwner();
            List<BabyGhastEntity> nearByBabyGhasts = this.owner.getLevel().getEntitiesOfClass(BabyGhastEntity.class, AABB.ofSize(new Vec3(this.owner.getX(), this.owner.getY(), this.owner.getZ()), 10, 10, 10), bg -> bg.isTame() && Objects.equals(bg.getOwner(), this.owner) && !bg.isHovering());
            this.effectLevel = nearByBabyGhasts.size() > 3 ? 3 : nearByBabyGhasts.size() - 1;
            return nearByBabyGhasts.size() < 4;
        }

        @Override
        public void tick() {
            owner.addEffect(new MobEffectInstance(NDEffects.VAMPIRISM.get(), 165, this.effectLevel));
        }
    }

    private static class TranslateBabyGhastIntoGhastGoal extends Goal {
        private final BabyGhastEntity babyGhast;
        private int timer = 0;

        public TranslateBabyGhastIntoGhastGoal(BabyGhastEntity babyGhast) { this.babyGhast = babyGhast; }

        @Override
        public boolean canUse() {
            Player nearestPlayer = this.babyGhast.getLevel().getNearestPlayer(this.babyGhast, 64);
            return (nearestPlayer != null || timer > 0) && !this.babyGhast.isTame();
        }

        @Override
        public void tick() {
            this.timer += 1;
            if (this.timer >= 4800) {
                Level level = this.babyGhast.getLevel();
                Ghast ghast = new Ghast(EntityType.GHAST, level);
                ghast.setPos(this.babyGhast.position());
                level.addFreshEntity(ghast);
                this.babyGhast.remove(RemovalReason.DISCARDED);
            }
        }
    }
}