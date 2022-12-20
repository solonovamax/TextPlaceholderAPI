package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class ColorNode extends ParentNode {
    @Nullable
    private final TextColor color;

    public ColorNode(TextNode[] children, @Nullable TextColor color) {
        super(children);
        this.color = color;
    }

    @NotNull
    @Override
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return out.setStyle(out.getStyle().withColor(this.color));
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new ColorNode(children, this.color);
    }
}
