all:
	rsync -avz -e ssh . bodhi@sunfire.comp.nus.edu.sg:/home/b/bodhi/public_html/MOGAT/ --exclude=".*/"
