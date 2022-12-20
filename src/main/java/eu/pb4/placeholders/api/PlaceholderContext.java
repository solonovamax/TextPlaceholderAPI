package eu.pb4.placeholders.api;


import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;


@ParametersAreNonnullByDefault
public record PlaceholderContext(MinecraftServer server,
                                 ServerCommandSource source,
                                 @Nullable ServerWorld world,
                                 @Nullable ServerPlayerEntity player,
                                 @Nullable Entity entity,
                                 @Nullable GameProfile gameProfile
) {


    public static final ParserContext.Key<PlaceholderContext> KEY = new ParserContext.Key<>("placeholder_context",
                                                                                            PlaceholderContext.class);

    @NotNull
    public static PlaceholderContext of(MinecraftServer server) {
        return new PlaceholderContext(
                server,
                server.getCommandSource(),
                null,
                null,
                null,
                null
        );
    }

    @NotNull
    public static PlaceholderContext of(GameProfile profile, MinecraftServer server) {
        var name = profile.getName() != null ? profile.getName() : profile.getId().toString();
        return new PlaceholderContext(
                server,
                new ServerCommandSource(
                        CommandOutput.DUMMY,
                        Vec3d.ZERO,
                        Vec2f.ZERO,
                        server.getOverworld(), // why do we set the world in the command source, by not the placeholder context?
                        server.getPermissionLevel(profile),
                        name,
                        Text.literal(name),
                        server,
                        null
                ),
                null,
                null,
                null,
                profile
        );
    }

    @NotNull
    public static PlaceholderContext of(ServerPlayerEntity player) {
        return new PlaceholderContext(
                Objects.requireNonNull(player.getServer()), // why is getServer nullable???
                player.getCommandSource(),
                player.getWorld(),
                player,
                player,
                player.getGameProfile()
        );
    }

    @NotNull
    public static PlaceholderContext of(ServerCommandSource source) {
        return new PlaceholderContext(
                source.getServer(),
                source,
                source.getWorld(),
                source.getPlayer(),
                source.getEntity(),
                source.getPlayer() != null ? source.getPlayer().getGameProfile() : null
        );
    }

    @NotNull
    public static PlaceholderContext of(Entity entity) {
        if (entity instanceof ServerPlayerEntity player) {
            return of(player);
        } else {
            return new PlaceholderContext(
                    Objects.requireNonNull(entity.getServer()), // why is getServer() nullable?
                    entity.getCommandSource(),
                    (ServerWorld) entity.getWorld(),
                    null, entity,
                    null
            );
        }
    }

    public boolean hasWorld() {
        return this.world != null;
    }

    public boolean hasPlayer() {
        return this.player != null;
    }

    public boolean hasGameProfile() {
        return this.gameProfile != null;
    }

    public boolean hasEntity() {
        return this.entity != null;
    }

    @NotNull
    public ParserContext asParserContext() {
        return ParserContext.of(KEY, this);
    }
}
