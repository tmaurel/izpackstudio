package views

import com.izforge.izpack.compiler.PackInfo
import org.jdesktop.swingx.treetable.DefaultTreeTableModel
import org.jdesktop.swingx.treetable.TreeTableNode
import javax.swing.tree.TreePath
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode
import javax.swing.tree.DefaultMutableTreeNode

/**
 * Created by IntelliJ IDEA.
 * User: Neoseifer
 * Date: 17 mars 2009
 * Time: 15:06:58
 * To change this template use File | Settings | File Templates.
 */

public class PacksTreeTableModel extends DefaultTreeTableModel
{

    PacksTreeTableModel(treeTableNode, objects)
    {
        super(treeTableNode, objects)  
    }

    public Object getValueAt(Object node, int column)
    {
        def res = ""


        if (node.getUserObject() instanceof PackInfo)
        {
            PackInfo pack = (PackInfo) node.getUserObject()
            switch (column)
            {
              case 0:
                  res = pack.getPack().name
                  break

              case 1:
                  if(pack.getPack().required)
                  {
                      res = "Required"
                  }
                  else if(pack.isPreselected())
                  {
                      res = "Preselected"
                  }
                  else
                  {
                      res = "Optional"
                  }
                  break

              case 2:
                  def desc = pack.getPack().description
                  if(desc.length() > 10)
                  {
                    desc = desc.substring(0,10) + "..."
                  }
                  res = desc
                  break

            }
        }
        else if(column == 0)
        {
            res = node.getUserObject()
        }

        return res;
    }

    public ArrayList getFilteredArray(Class className)
    {
        def res = new ArrayList()
        Traverse(getRoot(), res, className)
        return res
    }

    public void Traverse(Object o, ArrayList res, Class classN)
    {

        if(o instanceof DefaultMutableTreeTableNode)
        {
            if(classN.isInstance(o.getUserObject()))
            {
                res.add(o)
            }
        }


        for(def i=0; i<getChildCount(o); ++i)
        {
            Traverse(getChild(o, i), res, classN)
        }

    }

    public addChild(Object parent, Object o)
    {
        parent.add(o)
        modelSupport.fireTreeStructureChanged(new TreePath(getRoot())) 
    }
}