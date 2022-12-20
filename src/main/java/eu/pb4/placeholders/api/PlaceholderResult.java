package eu.pb4.placeholders.api;


import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public final class PlaceholderResult {
    private final Text text;

    private final String string;

    private final boolean valid;

    private PlaceholderResult(@Nullable Text text, @Nullable String reason) {
        if (text != null) {
            this.text = text;
            this.valid = true;
        } else {
            this.text = Text.literal("[" + (reason != null ? reason : "Invalid placeholder!") + "]")
                            .setStyle(Style.EMPTY.withColor(Formatting.GRAY)
                                                 .withItalic(true));
            this.valid = false;
        }
        this.string = this.text.getString();
    }

    /**
     * Create result for invalid placeholder
     *
     * @return PlaceholderResult
     */
    @NotNull
    public static PlaceholderResult invalid(String reason) {
        return new PlaceholderResult(null, reason);
    }

    /**
     * Create result for invalid placeholder
     *
     * @return PlaceholderResult
     */
    @NotNull
    public static PlaceholderResult invalid() {
        return new PlaceholderResult(null, null);
    }

    /**
     * Create result for placeholder with formatting
     *
     * @return PlaceholderResult
     */
    @NotNull
    public static PlaceholderResult value(Text text) {
        return new PlaceholderResult(text, null);
    }

    /**
     * Create result for placeholder
     *
     * @return PlaceholderResult
     */
    @NotNull
    public static PlaceholderResult value(String text) {
        return new PlaceholderResult(TextParserUtils.formatText(text), null);
    }

    /**
     * Returns text component from placeholder
     *
     * @return Text
     */
    public Text text() {
        return this.text;
    }

    /**
     * Returns text component as String (without formatting) from placeholder
     *
     * @return String
     */
    public String string() {
        return this.string;
    }

    /**
     * Checks if placeholder was valid
     *
     * @return boolean
     */
    public boolean isValid() {
        return this.valid;
    }
}


