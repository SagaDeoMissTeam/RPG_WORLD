//package net.sdm.sdm_rpg_world.mixin;
//
//import net.minecraftforge.event.TickEvent;
//import net.silentchaos512.scalinghealth.event.DifficultyEvents;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(value = DifficultyEvents.class, remap = false)
//public class scaleHealtMixin {
//
//
//
//    @Inject(method = "onWorldTick", at = @At("HEAD"), cancellable = true)
//    private static void sdm$onWorldTick(TickEvent.LevelTickEvent event, CallbackInfo ci){
//        ci.cancel();
//    }
//}
