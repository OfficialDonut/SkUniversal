package us._donut_.skuniversal;

import org.bukkit.plugin.java.JavaPlugin;
import us._donut_.skuniversal.autorank.AutorankRegister;
import us._donut_.skuniversal.bedwars.BedwarsRegister;
import us._donut_.skuniversal.clearlagg.ClearLagRegister;
import us._donut_.skuniversal.combatlog.CombatLogRegister;
import us._donut_.skuniversal.lockette.LocketteRegister;
import us._donut_.skuniversal.minepacks.MinePacksRegister;
import us._donut_.skuniversal.shopkeepers.ShopkeepersRegister;
import us._donut_.skuniversal.skywars.SkyWarsRegister;

public class SkUniversal extends JavaPlugin {

    @Override
    public void onEnable() {
        AutorankRegister.registerAutorank();
        BedwarsRegister.registerBedwars();
        ClearLagRegister.registerClearLagg();
        CombatLogRegister.registerCombatLog();
        LocketteRegister.registerLockette();
        MinePacksRegister.registerMinePacks();
        ShopkeepersRegister.registerShopekeepers();
        SkyWarsRegister.registerSkyWars();
        getLogger().info("Enabled!");
    }
    @Override
    public void onDisable() {
    }
}
