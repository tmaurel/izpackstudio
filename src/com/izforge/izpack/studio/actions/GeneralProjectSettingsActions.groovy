package com.izforge.izpack.studio.actions


actions
{
	action(
		id: 'addAuthor',
		closure: {
          def authors = authorsModel.getRowsModel().getValue()
          authors.add([name: "name", email: "email"])
          authorsModel.fireTableDataChanged()
        },
		shortDescription: 'Add an author',
        smallIcon: imageIcon(resource:"/images/add.png")
    )

	action(
		id: 'delAuthor',
		closure: {
          def authors = authorsModel.getRowsModel().getValue()
          def numSelected = tableAuthors.getSelectedRowCount()

            numSelected.times
            {
                def index = tableAuthors.getSelectedRow()
                authors.remove(index)
            }
          authorsModel.fireTableDataChanged()
        },
		shortDescription: 'Remove an author',
        smallIcon: imageIcon(resource:"/images/remove.png")
    )

}