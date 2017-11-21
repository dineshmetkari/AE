import React from 'react';
import ReactDom from 'react-dom';
import { Route, Router, browserHistory, IndexRoute } from 'react-router';
import Main from './container/Main';
import Header from './presentation/Header';
import BluePrint from './presentation/BluePrint';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

class Index extends React.Component{
	render() {
			return (	
				<MuiThemeProvider>
				<div>
				<Header />
				{this.props.children}
				</div>
				</MuiThemeProvider>		
			);
	}		
}
ReactDom.render(
<Router history = {browserHistory}>
<Route path = '/' component = {Index}>
<IndexRoute component = {Main} />
<Route path = '/blueprint' component = {BluePrint} />
</Route>
</Router>, document.getElementById('root')
);

