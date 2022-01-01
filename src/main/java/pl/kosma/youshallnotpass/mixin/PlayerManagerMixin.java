package pl.kosma.youshallnotpass.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.BannedPlayerList;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.kosma.youshallnotpass.YouShallNotPass;

import java.net.SocketAddress;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin
{
    @Shadow
    @Final
    private BannedPlayerList bannedProfiles;

    @Shadow public abstract boolean isWhitelisted(GameProfile profile);

    @Inject(method = "checkCanJoin", at = @At("HEAD"), cancellable = true)
    private void overrideWhitelistMessage(SocketAddress address, GameProfile profile,
                                          CallbackInfoReturnable<Text> cir)
    {
        if (! this.bannedProfiles.contains(profile) && ! this.isWhitelisted(profile))
        {
            cir.setReturnValue(new TranslatableText(YouShallNotPass.message));
        }
    }
}