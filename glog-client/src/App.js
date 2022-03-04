import styled from 'styled-components'
import { Switch, Route } from 'react-router-dom'
import Home from './pages/Home'
import Header from './components/common/Header'
import Profile from './pages/mypage/Profile'
import Write from './pages/board/Write'
import List from './pages/board/List'
import Detail from './pages/board/Detail'

function App() {
  return (
    <Switch>
      <Route path="post/write" exact component={Write} />
      <Route
        exact
        path="*"
        component={() => (
          <StyledWithHeader>
            <Header />
            <Route exact path="/" component={Home} />
            <Route path="/profile" component={Profile} />
            <Route path="/post/list" component={List} />
            <Route path="/post/detail" component={Detail} />
          </StyledWithHeader>
        )}
      />
    </Switch>
  )
}

const StyledWithHeader = styled.div`
  max-width: 1280px;
  margin: 0 auto;
  @media only screen and (min-width: 1920px) {
    width: 1280px;
  }
  @media only screen and (min-width: 1440px) {
    width: 1280px;
  }
  @media only screen and (min-width: 1280px) {
    width: 768px;
  }
  @media only screen and (min-width: 768px) {
    width: 100%;
  }
`

export default App
