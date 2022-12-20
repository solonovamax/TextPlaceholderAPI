package eu.pb4.placeholders.api.node;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.parent.ParentNode;
import eu.pb4.placeholders.impl.GeneralUtils;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public interface TextNode {
    @NotNull
    static TextNode convert(Text input) {
        return GeneralUtils.convertToNodes(input);
    }

    @NotNull
    static TextNode of(String input) {
        return new LiteralNode(input);
    }

    @NotNull
    static TextNode wrap(TextNode... nodes) {
        return new ParentNode(nodes);
    }

    @NotNull
    static TextNode asSingle(TextNode... nodes) {
        return switch (nodes.length) {
            case 0 -> EmptyNode.INSTANCE;
            case 1 -> nodes[0];
            default -> wrap(nodes);
        };
    }

    @NotNull
    static TextNode empty() {
        return EmptyNode.INSTANCE;
    }

    @NotNull
    Text toText(@Nullable ParserContext context, boolean removeSingleSlash);
}
