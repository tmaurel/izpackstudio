package com.izforge.izpack.studio.actions

import javax.swing.JFileChooser
import com.izforge.izpack.compiler.PackInfo
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode
import com.izforge.izpack.studio.views.AddPackProjectSettingsView



actions
{
	action(
		id: 'addPack',
		closure: {
            build(AddPackProjectSettingsView)
            addPackDialog.setVisible(true)
            addPackDialog.setModal(true)
        },
		shortDescription: 'Add a pack',
        smallIcon: imageIcon(resource:"/images/add.png")
    )

	action(
		id: 'delPack',
		closure: {
            def path = packTree.getPathForRow(packTree.getSelectedRow())
            if (path == null)
                return

            def node = path.getLastPathComponent()
            if(!(node.getUserObject() instanceof PackInfo))
                return

            def model = packTree.getTreeTableModel()
            model.removeNodeFromParent(node)
        },
		shortDescription: 'Remove a pack',
        smallIcon: imageIcon(resource:"/images/remove.png")
    )

	action(
		id: 'upPack',
		closure: {
            def path = packTree.getPathForRow(packTree.getSelectedRow())
            if (path == null)
                return

            def node = path.getLastPathComponent()
            if(!(node.getUserObject() instanceof PackInfo))
                return

            def model = packTree.getTreeTableModel()
            def parent = node.getParent()

            int index = model.getIndexOfChild(parent, node)

            if(index > 0)
            {
                model.removeNodeFromParent(node)
                model.insertNodeInto(node, parent, index-1)
            }
            else if(index == 0 && parent != model.getRoot())
            {
                def index2 = model.getIndexOfChild(parent.getParent(), parent)
                model.removeNodeFromParent(node)
                model.insertNodeInto(node, parent.getParent(), index2)
            }
        },
		shortDescription: 'Take this pack up',
        smallIcon: imageIcon(resource:"/images/up.png")
    )

	action(
		id: 'downPack',
		closure: {

            def path = packTree.getPathForRow(packTree.getSelectedRow())
            if (path == null)
                return

            def node = path.getLastPathComponent()
            if(!(node.getUserObject() instanceof PackInfo))
                return

            def model = packTree.getTreeTableModel()
            def parent = node.getParent()

            int index = model.getIndexOfChild(parent, node)


            if(index < parent.getChildCount() - 1)
            {
                model.removeNodeFromParent(node)
                model.insertNodeInto(node, parent, index+1)
            }
            else if(index == parent.getChildCount() - 1 && parent != model.getRoot())
            {
                def index2 = model.getIndexOfChild(parent.getParent(), parent)
                model.removeNodeFromParent(node)
                model.insertNodeInto(node, parent.getParent(), index2 + 1)
            }

        },
		shortDescription: 'Take this pack down',
        smallIcon: imageIcon(resource:"/images/down.png")
    )

	action(
		id: 'addFile',
		closure: {
            def fC = fileChooser(
                        id: 'myFileChooser',
                        dialogTitle: 'Select files/directories',
                        fileSelectionMode: JFileChooser.FILES_AND_DIRECTORIES,
                        multiSelectionEnabled: true
                     )

            if (fC.showOpenDialog(addPackDialog)==JFileChooser.APPROVE_OPTION)
            {
                def files = fC.getSelectedFiles()
                def model = filesModel.getRowsModel().getValue()

                files.each
                {
                    model.add([path: it.toString(), target: "\$INSTALL_PATH/"])
                }
                filesModel.fireTableDataChanged()

            }

        },
		shortDescription: 'Add a file',
        smallIcon: imageIcon(resource:"/images/add.png")
    )

	action(
		id: 'remFile',
		closure: {
            def model = filesModel.getRowsModel().getValue()
            def numSelected = tableFiles.getSelectedRowCount()

            numSelected.times
            {
                def index = tableFiles.getSelectedRow()
                model.remove(index)
            }

            filesModel.fireTableDataChanged()
        },
		shortDescription: 'Remove a file',
        smallIcon: imageIcon(resource:"/images/remove.png")
    )

	action(
		id: 'insertPack',
        name: 'OK',
		closure: {

          def packName = packName.getText()
          def packDesc = packDesc.getText()
          def behaviour = packBehaviour.getSelectedValue()
          def parent = packParent.getSelectedValue()
          def files = filesModel.getRows().toArray()

          def required = (behaviour == "Required")

          def pack = new PackInfo(packName, "", packDesc, required, false, "", true)
          pack.setPreselected((behaviour == "Preselected"))

          def node = new DefaultMutableTreeTableNode(pack)

          files.each
          {
              pack.addFile(null, new File(it.path), it.target, null, 0, null, "")
              node.add(new DefaultMutableTreeTableNode(it))
          }

          def model = packTree.getTreeTableModel()

          if(parent == "None")
          {
              model.addChild(model.getRoot(), node)
          }
          else
          {
              pack.setParent(parent.getUserObject().getPack().name)
              model.addChild(parent, node)
          }

          addPackDialog.dispose()
        },
		shortDescription: 'Save this Pack'
    )

	action(
		id: 'cancelPack',
        name: 'Close',
		closure: {
          addPackDialog.dispose()
        },
		shortDescription: 'Close'
    )

}