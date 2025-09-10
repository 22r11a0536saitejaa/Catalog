import java.nio.file.*;
import org.json.JSONObject;

public class catalog {
    public static void main(String[] args) {
        try {
            // Read JSON file (make sure input.json is in same directory)
            String jsonInput = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject obj = new JSONObject(jsonInput);

            // Extract n and k
            int n = obj.getJSONObject("keys").getInt("n");
            int k = obj.getJSONObject("keys").getInt("k");

            // Store first k roots
            long[] roots = new long[k];
            for (int i = 1; i <= k; i++) {
                JSONObject rootObj = obj.getJSONObject(String.valueOf(i));
                int base = Integer.parseInt(rootObj.getString("base"));
                String value = rootObj.getString("value");
                roots[i - 1] = Double.parseDouble(value, base); // convert to decimal
            }

            // Polynomial coefficients (degree = k)
            long[] coeff = new long[k + 1];
            coeff[0] = 1; // Leading coefficient

            // Expand polynomial with each root
            for (long r : roots) {
                for (int i = k; i >= 1; i--) {
                    coeff[i] = coeff[i] - r * coeff[i - 1];
                }
            }

            // Print result
            System.out.println("Polynomial coefficients:");
            for (int i = 0; i <= k; i++) {
                System.out.print(coeff[i] + " ");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
