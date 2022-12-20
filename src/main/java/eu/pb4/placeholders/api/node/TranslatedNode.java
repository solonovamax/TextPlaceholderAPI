package eu.pb4.placeholders.api.node;


import eu.pb4.placeholders.api.ParserContext;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public record TranslatedNode(String key, Object[] args) implements TextNode {
    public TranslatedNode(String key) {
        this(key, new Object[0]);
    }

    @NotNull
    @Override
    public Text toText(@Nullable ParserContext context, boolean removeSingleSlash) {
        var args = new Object[this.args.length];
        for (int i = 0; i < this.args.length; i++) {
            args[i] = this.args[i] instanceof TextNode textNode ? textNode.toText(context, true) : this.args[i];
        }

        return Text.translatable(this.key(), args);
    }
}
