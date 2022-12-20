package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class FontNode extends ParentNode {
    @Nullable
    private final Identifier font;

    public FontNode(TextNode[] children, @Nullable Identifier font) {
        super(children);
        this.font = font;
    }

    @NotNull
    @Override
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return out.setStyle(out.getStyle().withFont(font));
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new FontNode(children, this.font);
    }
}
