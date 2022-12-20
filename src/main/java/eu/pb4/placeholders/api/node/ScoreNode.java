package eu.pb4.placeholders.api.node;


import eu.pb4.placeholders.api.ParserContext;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public record ScoreNode(String name, String objective) implements TextNode {
    @NotNull
    @Override
    public Text toText(@Nullable ParserContext context, boolean removeSingleSlash) {
        return Text.score(name, objective);
    }
}
