import $ from 'jquery';


const React = require('react');
const ReactDOM = require('react-dom');


class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {factories: []};

        this.showFactoryForm = this.showFactoryForm.bind(this);
        this.hideFactoryForm = this.hideFactoryForm.bind(this);
        this.factoryFormSubmit = this.factoryFormSubmit.bind(this);
        this.hideChildForm = this.hideChildForm.bind(this);
        this.showChildForm = this.showChildForm.bind(this);
        this.childFormSubmit = this.childFormSubmit.bind(this);
        this.addFactory = this.addFactory.bind(this);
        this.addChildren = this.addChildren.bind(this);
    }

    loadTreeFromServer() {
        var self = this;
        $.ajax({
          url: "http://localhost:8080/api"
        }).then(function (data) {
          console.log(data);
          self.setState({factories: data.factories});
        });
      }


    componentDidMount() {
        this.loadTreeFromServer();
    }

    componentWillReceiveProps(nextProps, nextState) {
        if (this.state.data !== nextState.data) {
            this.setState(nextProps);
        }
    }

    showFactoryForm() {
        document.getElementById("addFactoryForm").style.display = '';
    }

    hideFactoryForm() {
        document.getElementById("addFactoryForm").style.display = "none";
    }

    showChildForm() {
        document.getElementById("addChildrenForm").style.display = '';
    }

    hideChildForm() {
        document.getElementById("addChildrenForm").style.display = "none";
    }

    factoryFormSubmit() {
        var form = document.getElementById("addFactoryForm");
        var name = form.elements["factory_name"].value;
        var rangeLow = form.elements["range_low"].value;
        var rangeHigh = form.elements["range_high"].value;
        this.hideFactoryForm();
        document.getElementById("addFactoryForm").reset();
        this.addFactory(name, rangeLow, rangeHigh);
    }

    childFormSubmit() {
            var form = document.getElementById("addChildrenForm");
            var factoryId = form.elements["factory_Id"].value;
            var numberOfChildren = form.elements["number_of_children"].value;
            this.hideChildForm();
            document.getElementById("addChildrenForm").reset();
            this.addChildren(factoryId, numberOfChildren);
        }

    addFactory(name, rangeLow, rangeHigh) {
        var self = this;
            $.ajax({
            method: "POST",
              url: "http://localhost:8080/api/create-factory",
              data: {name: name, rangeLow: rangeLow, rangeHigh: rangeHigh}
            }).then(function () {
              self.loadTreeFromServer();
              console.log("Factory added.");
            });
    }

    addChildren(factoryId, numberOfChildren) {
        var self = this;
            $.ajax({
                method: "POST",
                  url: "http://localhost:8080/api/create-children",
                  data: {factoryId: factoryId, numberOfChildren: numberOfChildren}
                }).then(function () {
                  self.loadTreeFromServer();
                  console.log("Children added.");
                });
    }

    render() {

        return(
        <div>
            <div id="addFactory">
                <span className="button"> <input type="button" value="Add Factory" onClick={this.showFactoryForm} /> </span>
                <span className="button"> <input type="button" value="Add Children" onClick={this.showChildForm} /> </span>
                <form id="addFactoryForm">
                    <h3>Add a new factory</h3>
                    <span>Name:  <input type="text" name="factory_name" id="factory_name" /></span>
                    <span>Range:
                                <input type="number" name="range_low" id="range_low" />
                                <input type="number" name="range_high" id="range_high" />
                            </span>

                    <input type="button" className="button" value="Add" onClick={this.factoryFormSubmit} />
                    <input type="reset" className="button" value="Cancel" onClick={this.hideFactoryForm} />
                </form>
                <form id="addChildrenForm">
                    <h3>Add children to a factory</h3>
                    <span>Factory ID:  <input type="number" name="factory_Id" id="factory_Id" /></span>
                    <span>Number of Children: <input type="number" name="number_of_children" id="number_of_children" /></span>

                     <input type="button" className="button" value="Generate Children" onClick={this.childFormSubmit} />
                     <input type="reset" className="button" value="Cancel" onClick={this.hideChildForm} />
                </form>
            </div>
            <FactoryList factories={this.state.factories}/>
        </div>
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