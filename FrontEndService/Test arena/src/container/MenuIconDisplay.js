import React from 'react';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import IconButton from 'material-ui/IconButton';
import Divider from 'material-ui/Divider';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';


/**
* Example of nested menus within an IconMenu.
*/




const MenuIconDisplay = () => (
 <IconMenu
   iconButtonElement={<IconButton><MoreVertIcon /></IconButton>}
   anchorOrigin={{horizontal: 'left', vertical: 'bottom'}}
   targetOrigin={{horizontal: 'left', vertical: 'bottom'}}
 >
   <MenuItem primaryText="Update Profile" />
   <Divider />
   <MenuItem value="Del" primaryText="Logout" />

 </IconMenu>
);

export default MenuIconDisplay;
