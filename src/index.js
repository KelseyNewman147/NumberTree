import $ from 'jquery';


const React = require('react');
const ReactDOM = require('react-dom');


class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {factories: []};
    }

    loadTreeFromServer() {
        var self = this;
        $.ajax({
          url: "https://numbertree.herokuapp.com/api"
        }).then(function (data) {
          console.log(data);
          self.setState({factories: data.factories});
        });
      }

    componentDidMount() {
        this.loadTreeFromServer();
    }

    render() {
        return(
            <FactoryList factories={this.state.factories}/>
        )
    }
}

class FactoryList extends React.Component {
    render() {
        var factories = this.props.factories.map(factory =>
            <Factory key={factory.id} factory={factory}/>
        );
        return(
            <ul>
                <li>{factories}</li>
            </ul>
        )
    }
}

class Factory extends React.Component {
    render() {
    var childNodes = this.props.factory.childNodes.map(childNode =>
            <p key={childNode.id} childnode={childNode}>{childNode.number}</p>
            );
        return(
            <ul>
                <li>{this.props.factory.id}</li>
                <li>{this.props.factory.name}</li>
                <li>{this.props.factory.rangeLow}</li>
                <li>{this.props.factory.rangeHigh}</li>
                <ul>
                    <li>{childNodes}</li>
                </ul>
            </ul>
        )
    }
}


ReactDOM.render(
    <App />,
    document.getElementById('root')
)