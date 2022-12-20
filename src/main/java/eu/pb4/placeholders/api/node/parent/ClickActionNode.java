package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.api.parsers.NodeParser;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class ClickActionNode extends ParentNode {
    private final ClickEvent.Action action;

    private final TextNode value;

    public ClickActionNode(TextNode[] children, ClickEvent.Action action, TextNode value) {
        super(children);
        this.action = action;
        this.value = value;
    }

    @NotNull
    public ClickEvent.Action action() {
        return action;
    }

    @NotNull
    public TextNode value() {
        return value;
    }

    @NotNull
    @Override
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return out.setStyle(out.getStyle().withClickEvent(new ClickEvent(this.action, this.value.toText(context, true).getString())));
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new ClickActionNode(children, this.action, this.value);
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children, NodeParser parser) {
        return new ClickActionNode(children, this.action, TextNode.asSingle(parser.parseNodes(this.value)));
    }
}
