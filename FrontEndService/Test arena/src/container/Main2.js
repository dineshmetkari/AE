import React from 'react';
import SearchQ from './SearchQ';
import BluePrint from './BluePrint';

export default class Main2 extends React.Component
{
	constructor(props)
	   {
		super(props);
		this.state={dems:[]};
	}
	render = () =>
	{
		if(this.state.dems === 0)
		{
		return(
			<div>
				<SearchQ questionData={this.preview} />
				</div>
				);
		}
		else
		{
			return(
				<div>
				<BluePrint vars={this.state.dems} />
			</div>
			);
		}
	}
	// this is a callback function...
	preview = (x) =>
	{
		this.setState({dems:x});
		console.log(this.state.dems+"mainaaaaa");
	}
}
