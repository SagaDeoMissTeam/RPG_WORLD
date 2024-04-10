package net.sdm.sdm_rpg_world.client;

import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.icon.Icons;
import net.sdm.sdm_rpg_world.core.aspect.Aspect;
import net.sdm.sdm_rpg_world.core.aspect.AspectsRegister;

import javax.swing.*;

public interface SDMIcons {

    Icon BACK = getAspect("_back");
    Icon UNKNOWN = getAspect("_unknown");
    Icon AER = getAspect("aer");
    Icon ALIENIS = getAspect("alienis");
    Icon ALKIMIA = getAspect("alkimia");
    Icon AQUA = getAspect("aqua");
    Icon AURAM = getAspect("auram");
    Icon AVERSIO = getAspect("aversio");
    Icon BESTIA = getAspect("bestia");
    Icon COGNITIO = getAspect("cognitio");
    Icon DESIDERIUM = getAspect("desiderium");
    Icon EXANIMIS = getAspect("exanimis");
    Icon FABRICO = getAspect("fabrico");
    Icon GELUM = getAspect("gelum");
    Icon HERBA = getAspect("herba");
    Icon HUMANUS = getAspect("humanus");
    Icon IGNIS = getAspect("ignis");
    Icon INSTRUMENTUM = getAspect("instrumentum");
    Icon LUX = getAspect("lux");
    Icon MACHINA = getAspect("machina");
    Icon METALLUM = getAspect("metallum");
    Icon MORTUUS = getAspect("mortuus");
    Icon MOTUS = getAspect("motus");
    Icon ORDO = getAspect("ordo");
    Icon PERDITIO = getAspect("perditio");
    Icon PERMUTATION = getAspect("permutatio");
    Icon POTENTIA = getAspect("potentia");
    Icon PRAECANTATIO = getAspect("praecantatio");
    Icon PRAEMUNIO = getAspect("praemunio");
    Icon SENSUS = getAspect("sensus");
    Icon SPIRITUS = getAspect("spiritus");
    Icon TENEBRAE = getAspect("tenebrae");
    Icon TERRA = getAspect("terra");
    Icon VACUOS = getAspect("vacuos");
    Icon VICTUS = getAspect("victus");
    Icon VINCULUM = getAspect("vinculum");
    Icon VITIUM = getAspect("vitium");
    Icon VITREUS = getAspect("vitreus");
    Icon VOLATUS = getAspect("volatus");
    Icon DISCORD = get("discord");
    Icon LOGO = get("logo");
    Icon SOCIAL = get("social");
    Icon ID_CARD = get("id-card");
    Icon BOOK_COVER = get("book-cover");
    Icon INFO = get("info");
    Icon STAR_ICON = get("staricon");
    Icon TEST = get("test");
    Icon SETTINGS = get("settings");

    static Icon get(String id) {
        return Icon.getIcon("sdm_rpg_world:icons/" + id);
    }
    static Icon getAspect(String id) {
        return Icon.getIcon("sdm_rpg_world:icons/aspects/" + id);
    }

    static Icon getImage(String id) {
        return Icon.getIcon("sdm_rpg_world:textures/icons/" + id + ".png");
    }
}
