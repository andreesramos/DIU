import "./HeaderComponent.css"

function HeaderComponent(props) {
  const { greetings, links } = props;

  return (
    <header className="header">
      <h1 className="title">{greetings}</h1>
      <nav>
        <ul className="header-list">
            <li>
                <a className="link" href="#">{links.home}</a>
            </li>
            <li>
                <a className="link" href="#">{links.blog}</a>
            </li>
            <li>
                <a className="link" href="#">{links.news}</a>
            </li>
            <li>
                <a className="link" href="#">{links.contact}</a>
            </li>
        </ul>
      </nav>
    </header>
  );
}

export default HeaderComponent;