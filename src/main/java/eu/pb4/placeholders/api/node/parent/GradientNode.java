package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.impl.GeneralUtils;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class GradientNode extends ParentNode {
    private final GradientProvider gradientProvider;

    public GradientNode(TextNode[] children, GradientProvider gradientBuilder) {
        super(children);
        this.gradientProvider = gradientBuilder;
    }

    @NotNull
    @Override
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return GeneralUtils.toGradient(out, this.gradientProvider);
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new GradientNode(children, this.gradientProvider);
    }

    @FunctionalInterface
    public interface GradientProvider {
        TextColor getColorAt(int index, int length);
    }
}
