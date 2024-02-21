package info.partonetrain.mixin;

import info.partonetrain.Knifestock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin extends Entity {

	@Inject(at = @At("HEAD"), method = "damage", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private void knifestock$checkPlayerHands(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if(source.getAttacker() instanceof PlayerEntity player){
			ItemStack mainhand = player.getStackInHand(Hand.MAIN_HAND);
			ItemStack offhand = player.getStackInHand(Hand.OFF_HAND);
			boolean notHoldingKnife = !(mainhand.isIn(Knifestock.ITEM_TAG) || offhand.isIn(Knifestock.ITEM_TAG));
			boolean isHit = source.getType().msgId().equals("player"); //this will be false if it was a projectile
			boolean isLivestock = this.getType().isIn(Knifestock.ENTITY_TYPE_TAG);

			if(isHit && isLivestock && notHoldingKnife){
				cir.setReturnValue(false);
			}
		}
	}

	protected LivingEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initDataTracker() {

	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {

	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {

	}
}