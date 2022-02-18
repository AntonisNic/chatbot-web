

class HistoryList extends React.Component {

    render() {
        if (!this.props.histories) {
            return <div className="temp">No questions yet...</div>
        }

        return (
            <div>
                <div className="temp">
                    You have have asked {this.props.histories.length} questions!
                </div>
                <table id="history-list" className="table" >
                    <thead>

                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Previous questions</th>

                    </tr>
                    </thead>
                    <tbody>
                    {this.props.histories.map((History_Class) => (

                        <tr>
                            <td> {History_Class.h_ID}</td>
                            <td> {History_Class.history_s}</td>

                        </tr>

                    ))}
                    </tbody>
                </table>
            </div>
        );
    }
}







class H_Main extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            histories: []
        }
    }
    componentDidMount() {
        fetch("/historylist")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        histories: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    render() {
        return (
            <div id="main">
                <HistoryList histories={this.state.histories}/>
            </div>
        );
    }
}











ReactDOM.render(
    <H_Main />,
    document.getElementById('history')
);
