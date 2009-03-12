package actions


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
          def indexes = tableAuthors.getSelectedRows()
          indexes.each
          {
              authors.remove(it)
          }
          authorsModel.fireTableDataChanged()
        },
		shortDescription: 'Remove an author',
        smallIcon: imageIcon(resource:"/images/remove.png")
    )

}