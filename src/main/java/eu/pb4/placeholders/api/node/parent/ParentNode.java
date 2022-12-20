package eu.pb4.placeholders.api.node.parent;


import eu.pb4.placeholders.api.ParserContext;
import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.impl.GeneralUtils;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public class ParentNode implements ParentTextNode {
    public static final ParentNode EMPTY = new ParentNode(new TextNode[0]);

    protected final TextNode[] children;

    public ParentNode(TextNode[] children) {
        this.children = children;
    }

    @NotNull
    protected Text applyFormatting(MutableText out, ParserContext context) {
        return out;
    }

    @NotNull
    @Override
    public ParentTextNode copyWith(TextNode[] children) {
        return new ParentNode(children);
    }

    @NotNull
    @Override
    public final TextNode[] getChildren() {
        return this.children;
    }

    @NotNull
    @Override
    public final Text toText(@Nullable ParserContext context, boolean removeSingleSlash) {
        var compact = context != null && context.get(ParserContext.Key.COMPACT_TEXT) != Boolean.FALSE;

        if (this.children.length == 0) {
            return Text.empty();
        } else if ((this.children.length == 1 && this.children[0] != null) && compact) {
            var out = this.children[0].toText(context, true);
            if (GeneralUtils.isEmpty(out)) {
                return out;
            }

            return ((MutableText) this.applyFormatting(out.copy(), context)).fillStyle(out.getStyle());
        } else {
            MutableText base = compact ? null : Text.empty();

            for (int i = 0; i < this.children.length; i++) {
                if (this.children[i] != null) {
                    var child = this.children[i].toText(context, true);

                    if (!GeneralUtils.isEmpty(child)) {
                        if (base == null) {
                            if (child.getStyle().isEmpty()) {
                                base = child.copy();
                            } else {
                                base = Text.empty();
                                base.append(child);
                            }
                        } else {
                            base.append(child);
                        }
                    }
                }
            }

            if (base == null || GeneralUtils.isEmpty(base)) {
                return Text.empty();
            }

            return this.applyFormatting(base, context);
        }
    }
}
