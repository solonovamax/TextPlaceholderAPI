package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.api.parsers.NodeParser;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;


@ParametersAreNonnullByDefault
public interface ParentTextNode extends TextNode {
    @NotNull
    ParentTextNode copyWith(TextNode[] children);

    @NotNull
    default ParentTextNode copyWith(TextNode[] children, NodeParser parser) {
        return copyWith(children);
    }

    @NotNull
    TextNode[] getChildren();

    @FunctionalInterface
    interface Constructor {
        @NotNull
        ParentTextNode createNode(String definition, Collection<ParentTextNode> children);
    }
}
