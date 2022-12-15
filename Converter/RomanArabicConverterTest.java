import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RomanArabicConverterTest {

    @Test
    public void testArabicToRoman() {
        String[] romans = { "MMXVIII", "CXXIII", "MMCCCXIII", "MMXXII", "X", "CDXC", "DCC", "MMCD", "MXCVIII",
                "MCMXCIX" };
        int[] actuals = { 2018, 123, 2313, 2022, 10, 490, 700, 2400, 1098, 1999 };
        int[] results = new int[10];

        for (int i = 0; i < 10; i++)
            results[i] = RomanArabicConverter.romanToArabic(romans[i]);

        assertArrayEquals(results, actuals);
    }

    @Test
    public void testRomanToArabic() {
        int[] arabic = { 2018, 123, 2313, 2022, 10, 490, 700, 2400, 1098, 1999 };
        String[] actuals = { "MMXVIII", "CXXIII", "MMCCCXIII", "MMXXII", "X", "CDXC", "DCC", "MMCD", "MXCVIII",
                "MCMXCIX" };
        String[] results = new String[10];

        for (int i = 0; i < 10; i++)
            results[i] = RomanArabicConverter.arabicToRoman(arabic[i]);

        assertArrayEquals(results, actuals);
    }

    @Test
    public void testUnsupportedRomanNumerals() {
        List<String> wrongRomanNumerals = Arrays.asList("A", "B", "E", "F", "G", "H", "J", "K", "N",
                "O", "P", "Q", "R", "S", "T", "U", "W", "Y", "Z");

        wrongRomanNumerals.forEach(
                (i) -> assertThrows(IllegalArgumentException.class,
                        () -> RomanArabicConverter.romanToArabic(i)));
    }

    @Test
    public void testSupportedRomanNumerals() {
        String[] romans = { "I", "V", "X", "L", "C", "D", "M" };
        int[] actuals = { 1, 5, 10, 50, 100, 500, 1000 };
        int[] results = new int[7];

        for (int i = 0; i < 7; i++)
            results[i] = RomanArabicConverter.romanToArabic(romans[i]);

        assertArrayEquals(results, actuals);
    }

    @Test
    public void testUnsupportedArabicRange() {
        List<Integer> arabic = Arrays.asList(0, -1, -4000, -4001, 4001, 10000);

        arabic.forEach(
                (i) -> assertThrows(IllegalArgumentException.class,
                        () -> RomanArabicConverter.arabicToRoman(i)));
    }
}
