package eu.pb4.placeholders.api.node;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.impl.textparser.TextParserImpl;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public record LiteralNode(String value) implements TextNode {

    public LiteralNode(StringBuilder builder) {
        this(builder.toString());
    }

    @NotNull
    @Override
    public Text toText(@Nullable ParserContext context, boolean removeSingleSlash) {
        if (removeSingleSlash) {
            var out = this.value();
            for (var e : TextParserImpl.ESCAPED_CHARS) {
                out = out.replace("\\" + e.left(), e.left());
            }
            return Text.literal(out);
        } else {
            return Text.literal(this.value());
        }
    }
}
