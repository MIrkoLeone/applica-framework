"use strict"

export class CardAction extends React.Component {
    perform() {
        this.props.action.action()
    }

    render() {
        return (
            <li><a href="javascript:;" onClick={this.perform.bind(this)}><i className={this.props.action.icon}></i></a></li>
        )
    }
}

export class Card extends React.Component {
    render() {
        let actionKey = 1

        return (
            <div className="card">
                {!_.isEmpty(this.props.title) || !_.isEmpty(this.props.actions) ?
                    <div className="card-header">
                        <h2>
                            {this.props.title}
                            {!_.isEmpty(this.props.subtitle) ?
                                <small>{this.props.subtitle}</small>
                                : null }
                        </h2>

                        {!_.isEmpty(this.props.actions) ?
                            <ul className="actions">
                                {this.props.actions.map(a => <CardAction key={actionKey++} action={a} />)}
                            </ul>
                        : null }
                    </div>
                    : null }

                {this.props.children}
            </div>
        )
    }
}