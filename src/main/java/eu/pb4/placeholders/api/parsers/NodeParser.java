package eu.pb4.placeholders.api.parsers;


import com.mojang.serialization.Codec;
import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.impl.textparser.MergedParser;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@ParametersAreNonnullByDefault
public interface NodeParser {
    NodeParser NOOP = i -> new TextNode[]{ i };

    @NotNull
    static NodeParser merge(NodeParser... parsers) {
        return switch (parsers.length) {
            case 0 -> NOOP;
            case 1 -> parsers[0];
            default -> new MergedParser(parsers);
        };
    }

    @NotNull
    static NodeParser merge(List<NodeParser> parsers) {
        return switch (parsers.size()) {
            case 0 -> NOOP;
            case 1 -> parsers.get(0);
            default -> new MergedParser(parsers.toArray(new NodeParser[0]));
        };
    }

    @NotNull
    TextNode[] parseNodes(TextNode input);

    @NotNull
    default Codec<WrappedText> codec() {
        return Codec.STRING.xmap(x -> WrappedText.from(this, x), WrappedText::input);
    }
}
