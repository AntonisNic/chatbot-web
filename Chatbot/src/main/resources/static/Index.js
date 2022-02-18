


class IndexList extends React.Component {

    render() {

    // alert(this.props.boom + "String" + this.props.flag);
        if (this.props.boom.length == ""){
            return <div className="temp">
                I cant answer that.Try again and make sure you added questions</div>
        }

        return (
            <div>

                <table id="history-list" className="table" >
                    <thead>

                    <tr>

                        <th scope="col">The answer</th>

                    </tr>
                    </thead>
                    <tbody>
                    {this.props.boom.map((Answer) => (

                        <tr>
                            <td> {Answer.answer_s}</td>

                        </tr>

                    ))}
                    </tbody>
                </table>
            </div>
        );
    }
}



class Index_Main extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            boom: [],
            flag: 0

        }
    }
    componentDidMount() {
        fetch("/answerlist")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        boom: response,
                        flag: 1
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    render() {
       // alert("return main")
        return (
            <div id="main">
                <IndexList boom={this.state.boom}  />
            </div>
        );
    }
}




ReactDOM.render(<Index_Main />,document.getElementById('index_root'));
