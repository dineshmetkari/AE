import React from 'react';
import ReactDom from 'react-dom';
import FontIcon from 'material-ui/FontIcon';
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import Paper from 'material-ui/Paper';
import IconLocationOn from 'material-ui/svg-icons/communication/location-on';


const recentsIcon = <FontIcon className="material-icons">restore</FontIcon>;
const favoritesIcon = <FontIcon className="material-icons">favorite</FontIcon>;
const nearbyIcon = <IconLocationOn />;
export default class Footer extends React.Component{
	 state = {
    selectedIndex: 0,
  };
  select = (index) => this.setState({selectedIndex: index});
	render(){
		return(
			<div>
			 <Paper zDepth={1}>

	        <BottomNavigation style={{backgroundColor:'#982d2d'}} selectedIndex={this.state.selectedIndex}>
	          <BottomNavigationItem
	            label="Recents"
	            icon={recentsIcon}
	            onClick={() => this.select(0)}
	          />
	          <BottomNavigationItem
	            label="Favorites"
	            icon={favoritesIcon}
	            onClick={() => this.select(1)}
	          />
	          <BottomNavigationItem
	            label="Nearby"
	            icon={nearbyIcon}
	            onClick={() => this.select(2)}
	          />
	        </BottomNavigation>
	      </Paper>
	      </div>

			);
	}
}
