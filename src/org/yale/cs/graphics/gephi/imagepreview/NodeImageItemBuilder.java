/*
Copyright 2012 Yale Computer Graphics Group
Authors : Yitzchak Lockerman
Website : http://graphics.cs.yale.edu/

DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

Copyright 2012 Yale Computer Graphics Group. All rights reserved.

The contents of this file are subject to the terms of the GNU
General Public License Version 3 only ("GPL" or "License"). 
You may not use this file except in compliance with the
License. You can obtain a copy of the License at /gpl-3.0.txt.
See the License for the specific language governing permissions and limitations 
under the License.  When distributing the software, include this License Header
Notice in each file and include the License file at /gpl-3.0.txt. 
If applicable, add the following below the License Header, with the fields
enclosed by brackets [] replaced by your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"

Contributor(s):

This file is based on, and meant to be used with, Gephi. (http://gephi.org/)
*/

package org.yale.cs.graphics.gephi.imagepreview;

import java.awt.Color;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.preview.api.Item;
import org.gephi.preview.plugin.items.NodeItem;
import org.gephi.preview.spi.ItemBuilder;
import org.openide.util.lookup.ServiceProvider;

/**
 * This is a simple class that creates an {@link ImageItem} for each node.
 * 
 * @author Yitzchak Lockerman
 */
@ServiceProvider(service=ItemBuilder.class)
public class NodeImageItemBuilder implements ItemBuilder{

    @Override
    public Item[] getItems(Graph graph, org.gephi.data.attributes.api.AttributeModel attributeModel) {

        Item[] items = new ImageItem[graph.getNodeCount()];
        
        int i = 0;
        for (Node n : graph.getNodes()) {
            ImageItem imageItem = new ImageItem((String)n.getNodeData().getAttributes().getValue("image"));
            imageItem.setData(NodeItem.X, n.getNodeData().x());
            imageItem.setData(NodeItem.Y, -n.getNodeData().y());
            imageItem.setData(NodeItem.Z, n.getNodeData().z());
            imageItem.setData(NodeItem.SIZE, n.getNodeData().getSize() * 2f);
            imageItem.setData(NodeItem.COLOR, new Color((int) (n.getNodeData().r() * 255),
                    (int) (n.getNodeData().g() * 255),
                    (int) (n.getNodeData().b() * 255),
                    (int) (n.getNodeData().alpha() * 255)));
            items[i++] = imageItem;
        }
        return items;
    }

    @Override
    public String getType() {
        return ImageItem.IMAGE;
    }
    
    
}
