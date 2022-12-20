package eu.pb4.placeholders.impl.placeholder;

import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.PlaceholderContext;
import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.placeholders.api.node.TextNode;
import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@ApiStatus.Internal
public record PlaceholderNode(String placeholder, Placeholders.PlaceholderGetter getter, boolean optionalContext, @Nullable String argument) implements TextNode {
    @NotNull
    @Override
    public Text toText(@Nullable ParserContext context, boolean removeSingleSlash) {
        var ctx = context.get(PlaceholderContext.KEY);
        var handler = getter.getPlaceholder(placeholder, context);
        return (ctx != null || this.optionalContext) && handler != null ? handler.onPlaceholderRequest(ctx, argument).text() : Text.empty();
    }
}
