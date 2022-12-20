package eu.pb4.placeholders.api;


import eu.pb4.placeholders.api.node.LiteralNode;
import eu.pb4.placeholders.api.node.parent.ParentNode;
import eu.pb4.placeholders.api.node.parent.ParentTextNode;
import eu.pb4.placeholders.api.parsers.TextParserV1;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class TextParserUtils {
    private TextParserUtils() {
    }

    @NotNull
    public static Text formatText(String text) {
        return formatNodes(text).toText(null, true);
    }

    @NotNull
    public static Text formatTextSafe(String text) {
        return formatNodesSafe(text).toText(null, true);
    }

    @NotNull
    public static Text formatText(String text, TextParserV1.TagParserGetter getter) {
        return formatNodes(text, getter).toText(null, true);
    }

    @NotNull
    public static ParentTextNode formatNodes(String text) {
        return new ParentNode(TextParserV1.DEFAULT.parseNodes(new LiteralNode(text)));
    }

    @NotNull
    public static ParentTextNode formatNodesSafe(String text) {
        return new ParentNode(TextParserV1.DEFAULT.parseNodes(new LiteralNode(text)));
    }

    @NotNull
    public static ParentTextNode formatNodes(String text, TextParserV1.TagParserGetter getter) {
        return new ParentNode(TextParserV1.parseNodesWith(new LiteralNode(text), getter));
    }
}
