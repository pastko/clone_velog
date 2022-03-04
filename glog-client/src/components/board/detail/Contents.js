import styled from 'styled-components'

function Contents() {
  return (
    <StyledContents></StyledContents>
    // <StyledContents dangerouslySetInnerHTML={{ __html: contents }}></StyledContents>
  )
}

const StyledContents = styled.div`
  margin-bottom: 4rem;
  p {
    text-align: left;
    margin-top: 50px;
    font-size: 1.3rem;
  }
`

export default Contents
