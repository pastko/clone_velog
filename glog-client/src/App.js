import styled from 'styled-components'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Header from './components/common/Header'
import Profile from './pages/mypage/Profile'
import Write from './pages/board/Write'
import List from './pages/board/List'
import Detail from './pages/board/Detail'

function App() {
  return (
    <BrowserRouter>
      <StyledWithHeader>
        <Header />
      </StyledWithHeader>
      <Routes>
        <Route path="/post/write" exact element={<Write />} />
      </Routes>
      <StyledWithHeader>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/post/list" element={<List />} />
          <Route path="/post/detail" element={<Detail />} />
        </Routes>
      </StyledWithHeader>
    </BrowserRouter>
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
