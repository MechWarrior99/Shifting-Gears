package shiftinggears.blueprints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.apache.commons.io.FileUtils;
import shiftinggears.block.SGBlocks;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ExpensiveKoala
 */
public class BlueprintManager {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Blueprint.class, new Blueprint.Serializer())
            .setPrettyPrinting()
            .create();

    public String[] prints = {"testing"};

    public static BlueprintManager instance;

    public List<Blueprint> blueprints;

    public static void init(File f) {
        try {
            if (!f.exists()) {
                instance = new BlueprintManager();
                instance.blueprints = new ArrayList<>();
                List<ItemStack> testingIngredients = new ArrayList<>();
                testingIngredients.add(new ItemStack(SGBlocks.block, 5, 2));
                testingIngredients.add(new ItemStack(SGBlocks.block, 3, 1));
                testingIngredients.add(new ItemStack(SGBlocks.block, 5, 0));
                instance.blueprints.add(new Blueprint("testing", testingIngredients));
                f.createNewFile();
            } else {
                instance = GSON.fromJson(new FileReader(f), BlueprintManager.class);
            }
            FileUtils.write(f, GSON.toJson(instance));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //ToDo: Remove blueprint recipe items from inventory
    public static boolean hasIngredients(EntityPlayer player, Blueprint print) {
        return true;
    }

    public static boolean removeIngredients(EntityPlayer player, Blueprint print) {
        return false;
    }
}
