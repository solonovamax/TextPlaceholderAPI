package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.api.parsers.NodeParser;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class InsertNode extends ParentNode {
    private final TextNode value;

    public InsertNode(TextNode[] children, TextNode value) {
        super(children);
        this.value = value;
    }

    @NotNull
    public TextNode value() {
        return this.value;
    }

    @NotNull
    @Override
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return out.setStyle(out.getStyle().withInsertion(value.toText(context, true).getString()));
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new InsertNode(children, this.value);
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children, NodeParser parser) {
        return new InsertNode(children, TextNode.asSingle(parser.parseNodes(this.value)));
    }
}
