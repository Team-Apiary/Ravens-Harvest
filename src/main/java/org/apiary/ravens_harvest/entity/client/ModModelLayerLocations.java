package org.apiary.ravens_harvest.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import org.apiary.ravens_harvest.RavensHarvest;

public class ModModelLayerLocations {
    public static final ModelLayerLocation RAVEN =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RavensHarvest.MODID, "raven"), "main");
}
