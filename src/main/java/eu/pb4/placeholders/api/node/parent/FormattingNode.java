package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class FormattingNode extends ParentNode {
    private final Formatting formatting;

    public FormattingNode(TextNode[] children, Formatting formatting) {
        super(children);
        this.formatting = formatting;
    }

    @NotNull
    @Override
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return out.formatted(this.formatting);
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new FormattingNode(children, this.formatting);
    }
}
