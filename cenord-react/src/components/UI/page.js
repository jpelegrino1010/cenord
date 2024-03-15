import Footer from '../footer/footer';
import Header from '../header/header';
import Navigation from '../navigation/navigation';

const Page = (props) => (
  <>
    <Header />
    <section className="layout">
      <Navigation className="side-bar" />
      <div className={props.pageClass}>{props.children}</div>
    </section>
    <Footer />
  </>
);

export default Page;
